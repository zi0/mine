<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<h1>마이페이지</h1>
ID : ${user.id } <br>
비밀번호 : ${user.password } <br>
이름 : ${user.name} <br>
등급 : ${user.role }
<a href="updateUserForm?id=${user.id }">수정</a>
<a href="deleteForm?id=${user.id }">삭제</a><p>
<a href="getUserList">목록으로</a>
</body>
</html>