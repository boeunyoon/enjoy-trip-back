package com.pjt.ssafybuddy.service.board;

import com.pjt.ssafybuddy.entity.board.BoardLike;
import com.pjt.ssafybuddy.mapper.BoardLikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardLikeServiceImpl implements BoardLikeService{
    private final BoardLikeMapper boardLikeMapper;

    @Override
    public int addLike(int boardId, String userId) {
        return boardLikeMapper.insert(boardId, userId);
    }

    @Override
    public int countLike(int boardId) {
        return boardLikeMapper.countLikes(boardId);
    }

    @Override
    public int delteLike(int boardId, String userId) {
        return boardLikeMapper.deleteLike(boardId, userId);
    }
}
