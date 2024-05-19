package com.pjt.ssafybuddy.controller.board;

import com.pjt.ssafybuddy.entity.board.Comment;
import com.pjt.ssafybuddy.service.board.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/post")
    public ResponseEntity<?> postComment(@RequestBody Comment comment){
        try{
            commentService.insertComment(comment);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        }catch (Exception e){
            return exceptionHandling(e);
        }
    }
    @GetMapping("/list/{boardId}")
    public ResponseEntity<?> getCommentList(@PathVariable int boardId){
        try{
            List<Comment> comments = commentService.selectAll(boardId);
            return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
        }catch (Exception e){
            return exceptionHandling(e);
        }
    }
    private ResponseEntity<String> exceptionHandling(Exception e){
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
