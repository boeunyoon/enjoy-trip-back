package com.pjt.ssafybuddy.service.board;

import com.pjt.ssafybuddy.dto.BoardDTO;
import com.pjt.ssafybuddy.entity.board.Board;
import com.pjt.ssafybuddy.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BoardService {
    int save(BoardDTO boardDTO);
    Board findById(int id);
    List<Board> findAllBoard();
}
