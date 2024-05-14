package com.pjt.ssafybuddy.controller.member;

import com.pjt.ssafybuddy.dto.LoginRequest;
import com.pjt.ssafybuddy.entity.member.Member;
import com.pjt.ssafybuddy.service.member.MemberService;
import com.pjt.ssafybuddy.util.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@AllArgsConstructor
@RequestMapping("/member")
@CrossOrigin("*")
public class MemberController {
    private final MemberService memberService;
    private final JWTUtil jwtUtil;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest user){
        try {
            String userId = user.getUserId();
            String userPw = user.getUserPw();
            Member member = memberService.login(userId, userPw);
            if(member != null){
                String accessToken = jwtUtil.createAccessToken(member.getUserId());
                String refreshToken = jwtUtil.createRefreshToken(member.getUserId());
                memberService.saveRefreshToken(userId, refreshToken);
                member.setAccessToken(accessToken);
                member.setRefreshToken(refreshToken);
                return new ResponseEntity<Member>(member, HttpStatus.OK);
            }else{
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            return exceptionHandling(e);
        }
    }
    @GetMapping("/{userId}")
    public ResponseEntity<?> findById(@PathVariable("userId") String id) throws SQLException{
        Member member = memberService.findByMemberId(id);
        return new ResponseEntity<Member>(member, HttpStatus.OK);
    }
    @PostMapping("/regist")
    public ResponseEntity<?> regist(@RequestBody Member member){
        try {
            if (memberService.regist(member) == 1) {
                return new ResponseEntity<Void>(HttpStatus.OK);
            }else{
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    @GetMapping("/logout")
    public ResponseEntity<?> logout(@RequestParam("userid") String userID){
        try {
            memberService.deleteRefreshToken(userID);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    private ResponseEntity<String> exceptionHandling(Exception e){
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
