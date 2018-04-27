package com.yedam.app.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface BoardService {
	
	public void insertBoard(BoardVO vo);
	public void updateBoard(BoardVO vo);
	public void deleteBoard(BoardVO vo);
	public BoardVO getBoard(BoardVO vo);
	public List<BoardVO> getBoardList(BoardVO vo);
	public void deleteBoardList(ArrayList<String> seq);
	public int getCount(BoardVO vo);
	public List<Map<String, Object>> getBoardListMap();
	
}
