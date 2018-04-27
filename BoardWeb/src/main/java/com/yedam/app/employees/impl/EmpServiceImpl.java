package com.yedam.app.employees.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.employees.EmpService;
import com.yedam.app.employees.EmpVO;

@Service("empService")
public class EmpServiceImpl implements EmpService {

	@Autowired
	EmpMyBatisDAO empdao;

	public List<EmpVO> getEmpList(EmpVO vo) {
		return empdao.getEmpList(vo);
	}

	public EmpVO getEmp(EmpVO vo) {
		return empdao.getEmp(vo);
	}

	@Override
	public void insertEmp(EmpVO vo) {
		empdao.insertEmp(vo);
	}

	@Override
	public void updateEmp(EmpVO vo) {
		empdao.updateEmp(vo);
	}

	@Override
	public void deleteEmp(EmpVO vo) {
		empdao.deleteEmp(vo);
	}
	
	public int getCount(EmpVO vo) {
		return empdao.getCount(vo);
	}
	
	public List<Map<String, Object>> getDeptEmpStatistic(EmpVO vo) {
		return empdao.getDeptEmpStatistic(vo);
	}
	
	public List<Map<String, Object>> getEmployeeAll() {
		return empdao.getEmployeeAll();
	}
}
