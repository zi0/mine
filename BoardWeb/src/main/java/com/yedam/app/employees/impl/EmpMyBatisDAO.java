package com.yedam.app.employees.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yedam.app.board.BoardVO;
import com.yedam.app.employees.EmpVO;

@Repository
public class EmpMyBatisDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public void insertEmp(EmpVO vo) {
		sqlSession.insert("emp.insertEmp", vo);
	}
	
	public void updateEmp(EmpVO vo) {
		sqlSession.update("emp.updateEmp", vo);
	}
	
	public void deleteEmp(EmpVO vo) {
		sqlSession.delete("emp.deleteEmp", vo);
	}
	
	public EmpVO getEmp(EmpVO vo) {
		return sqlSession.selectOne("emp.getEmp", vo);
	}
	
	public List<EmpVO> getEmpList(EmpVO vo) {			
		return sqlSession.selectList("emp.getEmpList", vo);
	}

	public int getCount(EmpVO vo) {
		return sqlSession.selectOne("emp.getCount", vo);
	}
	
	public List<Map<String, Object>> getDeptEmpStatistic(EmpVO vo) {
		return sqlSession.selectList("emp.getDeptEmpStatistic", vo);
	}
	
	public List<Map<String, Object>> getEmployeeAll(){
		System.out.println("mybatis getEmployeeAll");
		return sqlSession.selectList("emp.getEmployeeAll");
	}
}
