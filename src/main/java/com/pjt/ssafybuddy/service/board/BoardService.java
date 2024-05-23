package com.pjt.ssafybuddy.service.board;

import com.pjt.ssafybuddy.dto.BoardDTO;
import com.pjt.ssafybuddy.entity.board.Board;
import com.pjt.ssafybuddy.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface BoardService {
    int save(BoardDTO boardDTO);
    Board findById(int id);
    List<Board> findAllBoard();
    int boardLikeAdd(int boardId, String userId);
    int boardLikeDelete(int boardId, String userId);
    boolean userLikedPost(int boardId, String userId);
    List<Board> findPostByCategory(int category);
    List<Board> findPostBLikeCountSort(int category);
    List<Board> findPostBReadCountSort(int category);
    List<Board> findPostBCommentCountSort(int category);
    int updateReadCount(int boardId);
    List<Board> findPopularPost();
    List<Board> findRecentPopularPost();
    List<Board> selectSameCampus(int category, String campus);
}
