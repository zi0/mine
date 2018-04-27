<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
function go_list(page){
	document.getElementsByName("page")[0].value=page;
	// location.href="getBoardList?page="+page;
	document.forms[0].submit();
}

</script>
</head>
<body>
<a href="<%=request.getAttribute("javax.servlet.forward.request_uri") %>?lang=ko">한글</a>
<a href="<%=request.getAttribute("javax.servlet.forward.request_uri") %>?lang=en">영어</a>

	<h1><spring:message code="message.board.list.mainTitle" /></h1>
	<a href="insertBoard"><spring:message code="message.board.list.link.insertBoard"/></a>
	
	<a href="boardExcelView.do">엑셀 다운로드</a>
	
	<!-- 검색시작 -->
	<form action="getBoardList">
	<input type="hidden" name="page" value="1"/>
		<select name="searchCondition">
		<option value="">선택</option>
			<c:forEach items="${conditionMap }" var="option">
			<c:set var="sel" value=""/>
				<c:if test="${option.value==boardVO.searchCondition }"> 
					<c:set var="sel" value="selected='selected'"/> 
					</c:if>
						<option value="${option.value }" ${sel}>
				${option.key }</option>
			</c:forEach>
		</select>
		<input type="text" name="searchKeyword"/>
		<input type="submit" value="<spring:message code="message.board.list.search.condition.btn"/>"/>
	</form>
	<br>
	
	<form action="deleteBoardList">
	<input type="submit" value="<spring:message code="message.board.list.selDelete"/>"/><p>
	
	
	<c:forEach items="${boardList }" var="board">
	<input type="checkbox" value="${board.seq }" name="seq"/>
		${board.seq }  
		<a href="getBoard/${board.seq }">${board.title }</a>   
		${board.writer }
		${board.regDate }
		${board.uploadFileName }
		<br>
	</c:forEach>
<my:paging paging="${paging }" jsfunc="go_list"/>	
</form>
</body>
</html>