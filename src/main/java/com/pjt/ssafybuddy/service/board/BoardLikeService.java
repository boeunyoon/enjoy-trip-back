package com.pjt.ssafybuddy.service.board;

import com.pjt.ssafybuddy.entity.board.BoardLike;

public interface BoardLikeService {
    int addLike(int boardId, String userId);
    int countLike(int boardId);
    int delteLike(int boardId, String userId);
}
