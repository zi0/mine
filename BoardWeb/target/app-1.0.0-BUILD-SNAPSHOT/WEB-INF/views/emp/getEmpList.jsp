<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="my" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>전체 사원 조회</title>
<script>
	function go_list(page) {
		document.getElementsByName("page")[0].value = page;
		// location.href="getBoardList?page="+page;
		document.forms[0].submit();
	}
</script>
</head>
<body>
	<h1>전체 사원 조회</h1>
	<a href="insertEmp">등록페이지로</a>
	<p>
	<form action="getEmpList">
		<input type="hidden" name="page" value="1" />
		 <select name="searchCondition">
			<option value="">선택</option>
			<c:forEach items="${conditionMap }" var="option">
				<c:set var="sel" value="" />
				<c:if test="${option.value==empVO.searchCondition }">
					<c:set var="sel" value="selected='selected'" />
				</c:if>
				<option value="${option.value }" ${sel}>${option.key }</option>
			</c:forEach>
		</select> <input type="text" name="searchKeyword" /> 
		<input type="submit" value="검색" />
	</form>
	<br>


	<table>
		<tr>
			<td>ID</td>
			<td>NAME</td>
			<td>SALARY</td>
		</tr>
		<c:forEach items="${empList }" var="emp">
			<tr>
				<td><a href="getEmp?employee_id=${emp.employee_id }">${emp.employee_id }</a>
				</td>
				<td>${emp.first_name }  ${emp.last_name }</td>
				<td>${emp.salary }</td>
			</tr>
		</c:forEach>

	</table>
	<my:paging paging="${paging }" jsfunc="go_list" />
</body>
</html>