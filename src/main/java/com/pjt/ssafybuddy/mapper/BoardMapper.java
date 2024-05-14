package com.pjt.ssafybuddy.mapper;

import com.pjt.ssafybuddy.dto.BoardDTO;
import com.pjt.ssafybuddy.entity.board.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    int insert(BoardDTO boardDTO);
    Board selectById(int id);
    List<Board> findAllPost();
}
