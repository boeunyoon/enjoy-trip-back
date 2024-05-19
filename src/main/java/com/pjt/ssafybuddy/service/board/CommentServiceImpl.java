package com.pjt.ssafybuddy.service.board;

import com.pjt.ssafybuddy.entity.board.Comment;
import com.pjt.ssafybuddy.mapper.BoardMapper;
import com.pjt.ssafybuddy.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final CommentMapper commentMapper;
    private final BoardMapper boardMapper;
    @Override
    public int insertComment(Comment comment) {
        boardMapper.updateBoardComment(comment.getBoardId());
        return commentMapper.insert(comment);
    }

    @Override
    public int deleteComment(int commentId) {
        return commentMapper.delete(commentId);
    }

    @Override
    public List<Comment> selectAll(int boardId) {
        return commentMapper.selectAll(boardId);
    }
}
