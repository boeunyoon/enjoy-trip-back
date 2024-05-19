package com.pjt.ssafybuddy.mapper;

import com.pjt.ssafybuddy.dto.BoardDTO;
import com.pjt.ssafybuddy.entity.board.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    int insert(BoardDTO boardDTO);
    Board selectById(int id);
    List<Board> findAllPost();
    int updateBoardLike(int boardId);
    int deleteBoardLike(int boardId);
    int updateBoardComment(int boardId);
    List<Board> findPostByCategory(int category);
    List<Board> findPostBLikeCountSort(int category);
    List<Board> findPostBReadCountSort(int category);
    List<Board> findPostBCommentCountSort(int category);
    int updateReadCount(int id);
}
