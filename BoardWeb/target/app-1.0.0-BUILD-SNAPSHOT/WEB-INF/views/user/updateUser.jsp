<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>회원 정보 수정</h1>
<form action="updateForm">
	id : <input type="text" name="id" readonly="readonly" value="${user.id }"><br>
 	password : <input type="text" name="password" value="${user.password}"><br>
	name : <input type="text" name="name" value="${user.name }"><br>
	role : <input type="text" name="role" value="${user.role }"><br>
	<input type="submit" value="등록"/>
</form>
</body>
</html>