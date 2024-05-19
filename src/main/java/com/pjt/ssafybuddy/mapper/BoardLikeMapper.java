package com.pjt.ssafybuddy.mapper;

import com.pjt.ssafybuddy.entity.board.BoardLike;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BoardLikeMapper {
    int insert(@Param("boardId") int boardId, @Param("userId")String userId);
    int countLikes(int boardId);
    int deleteLike(@Param("boardId")int boardId,  @Param("userId")String userId);
    boolean existsLike(@Param("boardId") int boardId, @Param("userId") String userId);
}
