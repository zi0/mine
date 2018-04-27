<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>회원 등록</h1>
<form action="insertUser">
 	
 	id : <input type="text" name="id"><br>
	password : <input type="password" name="password"><br>
	name : <input type="text" name="writer"><br>
	role : <input type="text" name="role"><br><br>
	<input type="submit" value="등록"/>
	<a href="getUserList">목록으로</a>
</form>
</body>
</html>