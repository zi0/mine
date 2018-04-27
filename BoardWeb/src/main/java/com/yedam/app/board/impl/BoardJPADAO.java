package com.yedam.app.board.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.yedam.app.board.BoardVO;

@Repository
public class BoardJPADAO {
	@PersistenceContext		// 자동으로 추가, 수정, 삭제 매니저
	private EntityManager em;
	
	public void insertBoard(BoardVO vo) {
		System.out.println("JPA insertBoard");
		em.persist(vo);
	}
	
	public void updateBoard(BoardVO vo) {
		System.out.println("JPA updateBoard");
		em.merge(vo);
	}
	
	public void deleteBoard(BoardVO vo) {
		System.out.println("JPA deleteBoard");
		em.remove(em.find(BoardVO.class, vo.getSeq()));
	}

	public BoardVO getBoard(BoardVO vo) {
		System.out.println("JPA getBoard");
		return (BoardVO) em.find(BoardVO.class, vo.getSeq());
	}

	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("JPA getBoardList");
		// return em.createQuery("from BoardVO b order by b.seq desc").getResultList();
		TypedQuery<BoardVO> query ;
		if ("title".equals(vo.getSearchCondition()))
			query = em.createQuery("from BoardVO b  where b.title = :title order by b.seq desc", BoardVO.class);
		else if  ("content".equals(vo.getSearchCondition()))
			query = em.createQuery("from BoardVO b  where b.content = :title order by b.seq desc", BoardVO.class);
		else 
			query = em.createQuery("from BoardVO b  order by b.seq desc", BoardVO.class);
		if(vo.getSearchCondition() != null && !vo.getSearchCondition().equals(""))
			query.setParameter("title", vo.getSearchKeyword());
		query.setFirstResult(vo.getFirst()-1);
		query.setMaxResults(vo.getLast());
		return query.getResultList();
	}
	
	public void deleteBoardList(ArrayList<String> seq) {
	}
	
	public int getCount(BoardVO vo) {
		return ((Long)em.createQuery("select count(b) from BoardVO b").getSingleResult()).intValue();
	}
	
	public List<Map<String, Object>> getBoardListMap() {
		return null;
	}
}
