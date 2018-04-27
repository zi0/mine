package com.yedam.app.employees;

import java.util.List;
import java.util.Map;

public interface EmpService {

	public void insertEmp(EmpVO vo);
	public void updateEmp(EmpVO vo);
	public void deleteEmp(EmpVO vo);
	public EmpVO getEmp(EmpVO vo);
	public List<EmpVO> getEmpList(EmpVO vo);
	public int getCount(EmpVO vo);
	public List<Map<String, Object>> getDeptEmpStatistic(EmpVO vo);
	public List<Map<String, Object>> getEmployeeAll();
}
