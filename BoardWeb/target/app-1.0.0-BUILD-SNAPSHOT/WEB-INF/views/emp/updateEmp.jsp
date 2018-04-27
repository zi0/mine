<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert emp</title>
</head>
<body>
	<h1>사원 수정</h1>

	<form action="updateEmp">
		id : <input type="text" name="employee_id" readonly="readonly" value="${emp.employee_id }"><br> 
		first_name : <input	type="text" name="first_name"  value="${emp.first_name }"><br>
		last_name : <input type="text" name="last_name"  value="${emp.last_name }"><br>
		salary : <input type="text" name="salary"  value="${emp.salary }">
		<input type="submit" value="등록" />
	</form>
</body>
</html>
