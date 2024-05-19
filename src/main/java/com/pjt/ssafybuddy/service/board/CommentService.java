package com.pjt.ssafybuddy.service.board;

import com.pjt.ssafybuddy.entity.board.Comment;

import java.util.List;

public interface CommentService {
    int insertComment(Comment comment);
    int deleteComment(int commentId);
    List<Comment> selectAll(int boardId);
}
