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
    //인기 있는 게시글 찾기
    List<Board> findPopularPost();
    //인기 급상승
    List<Board> findRecentPopularPost();
    List<Board> selectMyPost(String userId);
    List<Board> selectMyLikedPost(String userId);
    //우리 게시판(같은 캠퍼스만 볼수있음)
    List<Board> selectSameCampus(int category, String campus);

}
