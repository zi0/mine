package com.yedam.app.board.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yedam.app.board.BoardVO;

@Repository
public class BoardMyBatisDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public void insertBoard(BoardVO vo) {
		sqlSession.insert("board.insertBoard", vo);
	}

	public void updateBoard(BoardVO vo) {
		sqlSession.update("board.updateBoard", vo);
	}

	public void deleteBoard(BoardVO vo) {
		sqlSession.delete("board.deleteBoard", vo);
	}

	public BoardVO getBoard(BoardVO vo) {
		return sqlSession.selectOne("board.getBoard", vo);
	}

	public List<BoardVO> getBoardList(BoardVO vo) {			// for문
		System.out.println("mybatis getBoardList");
		return sqlSession.selectList("board.getBoardList", vo);
	}
	
	public void deleteBoardList(ArrayList<String> seq) {
		sqlSession.delete("board.deleteBoardList", seq);
	}
	
	public int getCount(BoardVO vo) {
		return sqlSession.selectOne("board.getCount", vo);
	}
	
	public List<Map<String, Object>> getBoardListMap() {	
		System.out.println("mybatis getBoardList");
		return sqlSession.selectList("board.getBoardListMap");
	}
}
