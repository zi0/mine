<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>사원 상세정보</title>
</head>
<body>
<h3>사원 상세정보</h3>
사원번호 : ${emp.employee_id }<br>
사원명 : ${emp.first_name} ${emp.last_name} <br>
급여 : ${emp.salary} <br>
입사일자 : ${emp.hire_date} <br>

<a href="updateEmpForm?employee_id=${emp.employee_id }">수정</a>
<a href="deleteEmp?employee_id=${emp.employee_id }">삭제</a><p>
<a href="getEmpList">목록으로</a>
</body>
</html>