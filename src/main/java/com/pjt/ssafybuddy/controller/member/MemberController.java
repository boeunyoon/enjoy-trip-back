package com.pjt.ssafybuddy.controller.member;

import com.pjt.ssafybuddy.dto.LoginRequest;
import com.pjt.ssafybuddy.entity.member.Member;
import com.pjt.ssafybuddy.service.member.MemberService;
import com.pjt.ssafybuddy.service.s3.S3Service;
import com.pjt.ssafybuddy.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/member")
@CrossOrigin("*")
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final JWTUtil jwtUtil;
    private final S3Service s3Service;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest user) {
        try {
            String userId = user.getUserId();
            String userPw = user.getUserPw();
            Member member = memberService.login(userId, userPw);
            if (member != null) {
                String accessToken = jwtUtil.createAccessToken(member.getUserId());
                String refreshToken = jwtUtil.createRefreshToken(member.getUserId());
                memberService.saveRefreshToken(userId, refreshToken);
                member.setAccessToken(accessToken);
                member.setRefreshToken(refreshToken);
                return new ResponseEntity<Member>(member, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> findById(@PathVariable("userId") String id) throws SQLException {
        Member member = memberService.findByMemberId(id);
        log.debug("user id : {}", id);
        return new ResponseEntity<Member>(member, HttpStatus.OK);
    }

    @PostMapping("/regist")
    public ResponseEntity<?> regist(@RequestBody Member member) {
        try {
            if (memberService.regist(member) == 1) {
                return new ResponseEntity<Void>(HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(@RequestParam("userid") String userID) {
        try {
            memberService.deleteRefreshToken(userID);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody Member member, HttpServletRequest request) throws SQLException {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        String token = request.getHeader("refreshToken");
        if (jwtUtil.checkToken(token)) {
            if (token.equals(memberService.getRefreshToken(member.getUserId()))) {
                String accessToken = jwtUtil.createAccessToken(member.getUserId());
                log.debug("token : {}", accessToken);
                log.debug("정상적으로 재발급");
                resultMap.put("access-token", accessToken);
                status = HttpStatus.CREATED;
            }
        } else {
            log.debug("refresh token도 사용 불가");
            status = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllUsersExceptCurrent(@RequestParam("currentUserId") String currentUserId) {
        try {
            List<Member> members = memberService.findAllExceptUser(currentUserId);
            return new ResponseEntity<List<Member>>(members, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/not-in-group/{groupId}")
    public ResponseEntity<?> getMembersNotInGroup(@PathVariable int groupId) {
        try {
            List<Member> members = memberService.findMembersNotInGroup(groupId);
            return new ResponseEntity<List<Member>>(members, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/all-ids")
    public ResponseEntity<?> getAllUserIds() {
        try {
            List<String> userIds = memberService.findAllUserIds();
            return new ResponseEntity<List<String>>(userIds, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @PostMapping("/uploadProfileImage")
    public ResponseEntity<?> uploadProfileImage(@RequestParam("userId") String userId, @RequestParam("file") MultipartFile multipartFile) {
        try {
            File file = convertMultiPartToFile(multipartFile);
            String imageUrl = s3Service.uploadFile(file);
            memberService.updateProfileImage(userId, imageUrl);
            return new ResponseEntity<>(imageUrl, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private File convertMultiPartToFile(MultipartFile file) {
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
        try {
            file.transferTo(convFile);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert MultipartFile to File", e);
        }
        return convFile;
    }
}
