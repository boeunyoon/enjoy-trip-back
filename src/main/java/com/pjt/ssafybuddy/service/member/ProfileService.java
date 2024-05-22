package com.pjt.ssafybuddy.service.member;

import com.pjt.ssafybuddy.entity.board.Board;

import java.util.List;

public interface ProfileService {
    List<Board> getMyPost(String userId);
    List<Board> myLikePost(String userId);
}
