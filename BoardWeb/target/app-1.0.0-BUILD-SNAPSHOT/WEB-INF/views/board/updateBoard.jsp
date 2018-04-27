<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>수정</h1>
<form action="updateBoard">
	seq : <input type="text" name="seq" readonly="readonly" value="${board.seq }"><br>
 	title : <input type="text" name="title" value="${board.title}"><br>
	writer : ${board.writer }<br>
	content : <textarea rows="5" cols="40" name="content">${board.content }</textarea><br>
	attach : ${board.uploadFileName }
	<input type="submit" value="등록"/>
</form>
</body>
</html>