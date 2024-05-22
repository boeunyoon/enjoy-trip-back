package com.pjt.ssafybuddy.service.member;

import com.pjt.ssafybuddy.entity.board.Board;
import com.pjt.ssafybuddy.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService{
    private final BoardMapper boardMapper;
    @Override
    public List<Board> getMyPost(String userId) {
        return boardMapper.selectMyPost(userId);
    }

    @Override
    public List<Board> myLikePost(String userId) {
        return boardMapper.selectMyLikedPost(userId);
    }
}
