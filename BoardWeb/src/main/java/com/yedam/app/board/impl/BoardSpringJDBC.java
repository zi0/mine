package com.yedam.app.board.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yedam.app.board.BoardVO;

@Repository
public class BoardSpringJDBC {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// SQL 명령어들
    private final String BOARD_INSERT = "insert into board(seq, title, writer, content, regdate ) values(?,?,?,?,sysdate)";
    private final String BOARD_UPDATE = "update board set title=?, content=?, writer=? where seq=?";
    private final String BOARD_DELETE = "delete board where seq=?";
    private final String BOARD_GET    = "select * from board where seq=?";
    private final String BOARD_LIST = "select * from board order by seq desc";
    private final String BOARD_LIST_T = "select * from board where title like '%'||?||'%' order by seq desc";
    private final String BOARD_LIST_C = "select * from board where content like '%'||?||'%' order by seq desc";

    // 글 등록
    public void insertBoard(BoardVO vo) {
    	System.out.println("=========spring jdbc insert===========");
    	Object[] args = 
    			{vo.getSeq()
    			,vo.getTitle()
    			,vo.getWriter()
    			,vo.getContent()};
    	
    	jdbcTemplate.update(BOARD_INSERT,args);
    }
    
    // 글 수정
    public void updateBoard(BoardVO vo) {
    	System.out.println("=========spring jdbc update===========");
    	jdbcTemplate.update(BOARD_UPDATE
    			,vo.getTitle()
    			,vo.getContent()
    			,vo.getWriter()
    			,vo.getSeq());
    }
    
    // 글 삭제
    public void deleteBoard(BoardVO vo){
    	System.out.println("=========spring jdbc delete===========");
    	jdbcTemplate.update(BOARD_DELETE
    			,vo.getSeq());
    }
    
    // 글 상세 조회
    public BoardVO getBoard(BoardVO vo){
    	System.out.println("=========spring jdbc getBoard===========");
    	Object[] args = {vo.getSeq()}; 		// 다수도 가능
    	return jdbcTemplate.queryForObject(BOARD_GET, args, new BoardRowMapper());
    }
    
    // 글 목록 조회
    public List<BoardVO> getBoardList(BoardVO vo){
    	System.out.println("=========spring jdbc getBoardList===========");
    	
    	if("title".equals(vo.getSearchCondition())) {
    		Object[] args = {vo.getSearchKeyword()};
    		return jdbcTemplate.query(BOARD_LIST_T, args, new BoardRowMapper());
    	} else if ("content".equals(vo.getSearchCondition())) {
    		Object[] args = {vo.getSearchKeyword()};
    		return jdbcTemplate.query(BOARD_LIST_C, args, new BoardRowMapper());
    	} else {
        	return jdbcTemplate.query(BOARD_LIST, new BoardRowMapper());
    	}
    }
}



class BoardRowMapper implements RowMapper<BoardVO>{

	@Override
	public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BoardVO board = new BoardVO();
		board.setSeq(rs.getInt("SEQ"));
        board.setTitle(rs.getString("TITLE"));
        board.setWriter(rs.getString("WRITER"));
        board.setContent(rs.getString("CONTENT"));
        board.setRegDate(rs.getDate("REGDATE"));
        board.setCnt(rs.getInt("CNT"));
		return board;
	}
}
