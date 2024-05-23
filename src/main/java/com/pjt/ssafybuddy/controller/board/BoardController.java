package com.pjt.ssafybuddy.controller.board;

import com.pjt.ssafybuddy.dto.BoardDTO;
import com.pjt.ssafybuddy.entity.board.Board;
import com.pjt.ssafybuddy.entity.board.BoardLike;
import com.pjt.ssafybuddy.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @GetMapping("/list")
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
    @PostMapping("/like/add")
    public ResponseEntity<?> addBoardLike(@RequestBody BoardLike boardLike){
        try{
            String userId = boardLike.getUserId();
            int boardId = boardLike.getBoardId();
            boardService.boardLikeAdd(boardId, userId);
            return ResponseEntity.ok("OK");
        }catch (Exception e){
            return exceptionHandling(e);
        }
    }
    @PostMapping("/like/delete")
    public ResponseEntity<?> deleteBoardLike(@RequestBody BoardLike boardLike){
        try{
            String userId = boardLike.getUserId();
            int boardId = boardLike.getBoardId();
            boardService.boardLikeDelete(boardId, userId);
            return ResponseEntity.ok("OK");
        }catch (Exception e){
            return exceptionHandling(e);
        }
    }
    @GetMapping("/liked/{boardId}")
    public ResponseEntity<?> userLikedPost(@PathVariable int boardId, @RequestParam String userId){
        boolean check = boardService.userLikedPost(boardId, userId);
        return new ResponseEntity<>(check, HttpStatus.OK);
    }
    @GetMapping("/list/{category}")
    public ResponseEntity<?> getListByCategory(@PathVariable int category,
                                               @RequestParam("campus") String campus){
        try{
            List<Board> result = null;
            if(category == 2){
                result = boardService.selectSameCampus(category, campus);
            }else{
                result = boardService.findPostByCategory(category);
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return exceptionHandling(e);
        }
    }
    @GetMapping("/list/sort/{category}")
    public ResponseEntity<?> getSortedList(
            @PathVariable int category,
            @RequestParam("sorted")String sorted
    ){
        try{
            List<Board> result = null;
            if(sorted.equals("like")){
                result = boardService.findPostBLikeCountSort(category);
            }else if(sorted.equals("read")){
                result = boardService.findPostBReadCountSort(category);
            }else if(sorted.equals("comment")){
                result = boardService.findPostBCommentCountSort(category);
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return exceptionHandling(e);
        }
    }
    @PutMapping("/add/views/{id}")
    public ResponseEntity<?> addViewCount(@PathVariable int id){
        try{
            boardService.updateReadCount(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return exceptionHandling(e);
        }
    }
    @GetMapping("/list/hot")
    public ResponseEntity<?> getHotPost(){
        try{
            List<Board> result = boardService.findPopularPost();
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return exceptionHandling(e);
        }
    }
    @GetMapping("/list/recent")
    public ResponseEntity<?> getRecentHot(){
        try{
            List<Board> result = boardService.findRecentPopularPost();
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
