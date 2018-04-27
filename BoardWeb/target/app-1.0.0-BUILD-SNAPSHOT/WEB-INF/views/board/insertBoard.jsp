<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert board</title>
</head>
<body>
	<h1>등록</h1>

	<form action="insertBoard" method="post" enctype="multipart/form-data">
		id : <input type="text" name="title"><br> 
		writer : <input	type="text" name="writer"><br>
		content : <textarea rows="5" cols="40" name="content"></textarea><br>
		attach : <input type="file" name="uploadFile"/> 
		<input type="submit" value="등록" />
	</form>
</body>
</html>