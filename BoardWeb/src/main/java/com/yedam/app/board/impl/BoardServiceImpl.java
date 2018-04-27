package com.yedam.app.board.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.board.BoardService;
import com.yedam.app.board.BoardVO;

@Service("boardservice")
public class BoardServiceImpl implements BoardService {

	@Autowired
	// BoardDAO dao ;
	// BoardSpringJDBC dao;
	// BoardMyBatisDAO dao;
	BoardJPADAO dao;

	@Override
	public void insertBoard(BoardVO vo) {
		dao.insertBoard(vo);
	}

	@Override
	public void updateBoard(BoardVO vo) {
		dao.updateBoard(vo);
	}

	@Override
	public void deleteBoard(BoardVO vo) {
		dao.deleteBoard(vo);
	}

	@Override
	public BoardVO getBoard(BoardVO vo) {
		return dao.getBoard(vo);
	}

	@Override
	public List<BoardVO> getBoardList(BoardVO vo) {
		return dao.getBoardList(vo);
	}

	@Override
	public void deleteBoardList(ArrayList<String> seq) {
		dao.deleteBoardList(seq);
	}

	public int getCount(BoardVO vo) {
		return dao.getCount(vo);
	}

	@Override
	public List<Map<String, Object>> getBoardListMap() {
		return dao.getBoardListMap();
	}
}
