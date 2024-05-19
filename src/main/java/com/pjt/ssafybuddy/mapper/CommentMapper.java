package com.pjt.ssafybuddy.mapper;

import com.pjt.ssafybuddy.entity.board.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    int insert(Comment comment);
    int delete(int commentId);
    List<Comment> selectAll(int boardId);
}
