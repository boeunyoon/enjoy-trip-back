package com.pjt.ssafybuddy.service.board;

import com.pjt.ssafybuddy.dto.BoardDTO;
import com.pjt.ssafybuddy.entity.board.Board;
import com.pjt.ssafybuddy.mapper.BoardLikeMapper;
import com.pjt.ssafybuddy.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardMapper boardMapper;
    private final BoardLikeMapper boardLikeMapper;
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

    @Transactional
    @Override
    public int boardLikeAdd(int boardId, String userId) {
        boardLikeMapper.insert(boardId, userId);
        return boardMapper.updateBoardLike(boardId);
    }
    @Transactional
    @Override
    public int boardLikeDelete(int boardId, String userId) {
        boardLikeMapper.deleteLike(boardId, userId);
        return boardMapper.deleteBoardLike(boardId);
    }

    @Override
    public boolean userLikedPost(int boardId, String userId) {
        return boardLikeMapper.existsLike(boardId, userId);
    }

    @Override
    public List<Board> findPostByCategory(int category) {
        return boardMapper.findPostByCategory(category);
    }

    @Override
    public List<Board> findPostBLikeCountSort(int category) {
        return boardMapper.findPostBLikeCountSort(category);
    }

    @Override
    public List<Board> findPostBReadCountSort(int category) {
        return boardMapper.findPostBReadCountSort(category);
    }

    @Override
    public List<Board> findPostBCommentCountSort(int category) {
        return boardMapper.findPostBCommentCountSort(category);
    }

    @Override
    public int updateReadCount(int boardId) {
        return boardMapper.updateReadCount(boardId);
    }

    @Override
    public List<Board> findPopularPost() {
        return boardMapper.findPopularPost();
    }

    @Override
    public List<Board> findRecentPopularPost() {
        return boardMapper.findRecentPopularPost();
    }

    @Override
    public List<Board> selectSameCampus(int category, String campus) {
        return boardMapper.selectSameCampus(category, campus);
    }
}
