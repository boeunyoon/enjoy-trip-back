package com.pjt.ssafybuddy.controller.member;

import com.pjt.ssafybuddy.entity.board.Board;
import com.pjt.ssafybuddy.service.member.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
    private final ProfileService profileService;
    @GetMapping("/post/{userId}")
    public ResponseEntity<?> getMyPost(@PathVariable String userId){
        try{
            List<Board> result = profileService.getMyPost(userId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return exceptionHandling(e);
        }
    }
    @GetMapping("/like/post/{userId}")
    public ResponseEntity<?> getLikedPost(@PathVariable String userId){
        try{
            List<Board> result = profileService.myLikePost(userId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return exceptionHandling(e);
        }
    }
    private ResponseEntity<String> exceptionHandling(Exception e){
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
