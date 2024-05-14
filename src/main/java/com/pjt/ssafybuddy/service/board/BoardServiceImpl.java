package com.pjt.ssafybuddy.service.board;

import com.pjt.ssafybuddy.dto.BoardDTO;
import com.pjt.ssafybuddy.entity.board.Board;
import com.pjt.ssafybuddy.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardMapper boardMapper;
    @Override
    public int save(BoardDTO boardDTO) {
        System.out.println(boardDTO);
        return boardMapper.insert(boardDTO);
    }

    @Override
    public Board findById(int id) {
        return boardMapper.selectById(id);
    }

    @Override
    public List<Board> findAllBoard() {
        return boardMapper.findAllPost();
    }
}
