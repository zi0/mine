<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>사원 등록</title>
</head>
<body>
<form action="insertEmp" method="post">
		사번 : <input type="text" name="employee_id" id="employee_id"/> <br>
		이름 : <input type="text" name="first_name" id="first_name"/> <br>
		성 : <input type="text" name="last_name" id="last_name"/> <br>
		이메일 : <input type="text" name="email" id="email"/> <br>
		직업 : <input type="text" name="job_id" id="job_id"/>
			
		
		<br><br>
		<input type="submit" value="등록"/>
</form>
</body>
</html>