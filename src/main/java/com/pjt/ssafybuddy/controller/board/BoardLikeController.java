package com.pjt.ssafybuddy.controller.board;

import com.pjt.ssafybuddy.entity.board.BoardLike;
import com.pjt.ssafybuddy.service.board.BoardLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardLikeController {
    private final BoardLikeService boardLikeService;
//    @PostMapping("/board/like/add")
    public ResponseEntity<?> addBoardLike(@RequestBody BoardLike boardLike){
        try{
            String userId = boardLike.getUserId();
            int boardId = boardLike.getBoardId();
            boardLikeService.addLike(boardId, userId);
            return ResponseEntity.ok("OK");
        }catch (Exception e){
            return exceptionHandling(e);
        }
    }
//    @DeleteMapping("/board/like/delte")
    public ResponseEntity<?> deleteBoardLike(@RequestBody BoardLike boardLike){
        try{
            String userId = boardLike.getUserId();
            int boardId = boardLike.getBoardId();
            boardLikeService.delteLike(boardId, userId);
            return ResponseEntity.ok("OK");
        }catch (Exception e){
            return exceptionHandling(e);
        }
    }
    private ResponseEntity<String> exceptionHandling(Exception e){
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
