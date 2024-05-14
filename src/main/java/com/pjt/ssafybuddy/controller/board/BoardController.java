package com.pjt.ssafybuddy.controller.board;

import com.pjt.ssafybuddy.dto.BoardDTO;
import com.pjt.ssafybuddy.entity.board.Board;
import com.pjt.ssafybuddy.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    @PostMapping("/post")
    public ResponseEntity<?> saveBoard(@RequestBody BoardDTO boardDTO){
        try{
            boardService.save(boardDTO);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }catch (Exception e){
            return exceptionHandling(e);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findBoardById(@PathVariable final int id){
        try{
            Board board = boardService.findById(id);
            return new ResponseEntity<Board>(board, HttpStatus.OK);
        }catch (Exception e){
            return exceptionHandling(e);
        }
    }
    @GetMapping("list")
    public ResponseEntity<?> findAll(){
        try{
            List<Board> list = boardService.findAllBoard();
            if(list.size() == 0){
                return new ResponseEntity<String>("게시글이 없습니다.", HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<List<Board>>(list, HttpStatus.OK);
            }
        }catch (Exception e){
            return exceptionHandling(e);
        }
    }
    private ResponseEntity<String> exceptionHandling(Exception e){
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
