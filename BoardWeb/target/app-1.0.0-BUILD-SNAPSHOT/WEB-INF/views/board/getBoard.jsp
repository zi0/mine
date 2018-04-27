<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<style>
#comments{
	border: 1px dotted blue;
}
</style>
<script src="${pageContext.request.contextPath }/scripts/jquery-3.3.1.min.js"></script>
<script>
window.onload = function() {
	loadCommentList();  // 목록조회 ajax 요청
}
var context = '${pageContext.request.contextPath}';
//목록조회 요청
function loadCommentList() {
	var params = { boardSeq : '${board.seq}' };
	$.getJSON(context + "/getCommentsList", params, function(datas){
		for(i=0; i<datas.length; i++){
			$("#commentList").append(makeCommentView(datas[i]));
		}
	});
}
function makeCommentView(comment){
	var div = document.createElement("div"); 
	div.setAttribute("id", "c"+comment.seq);
	div.className = 'comment';
	div.comment = comment;  //{id:1,.... }
	
	var str = "<strong>" + comment.name + "</strong> " + comment.content
+"<input type=\"button\" value=\"수정\" onclick=\"viewUpdateForm('"+comment.seq+"')\"/>"
+"<input type=\"button\" value=\"삭제\" onclick=\"confirmDeletion('"+comment.seq+"')\"/>"
	div.innerHTML = str;
	return div;
}
function addComment(){
	var params = $("[name=addForm]").serialize();
	$.getJSON(context + "/insertComments", params, function(datas){
		$("#commentList").append(makeCommentView(datas));
		documents.addForm.reset();
	});
}
//수정버튼 이벤트핸들러: 수정할 댓글밑에 수정폼 보이게 함
function viewUpdateForm(commentId) {
	var commentDiv = document.getElementById('c'+commentId);
	var updateFormDiv = document.getElementById('commentUpdate');
	//현재 수정상태(수정폼이 보이고 있음)이면 수정폼이 보이게 할 필요 없음
	if (updateFormDiv.parentNode != commentDiv) {
		commentDiv.appendChild(updateFormDiv);  //수정폼을 수정할 댓글밑에 보이도록
	}
	//수정할 값을 텍스트필드에 보이게
	var comment = commentDiv.comment;   //댓글 객체 { id:'', content:'', name:'' }
	document.updateForm.seq.value = comment.seq;    
	document.updateForm.name.value = comment.name;
	document.updateForm.content.value = comment.content;
	updateFormDiv.style.display = '';   //수정폼 보이게
}

function updateComment(){
	var params = $("[name=updateForm]").serialize();
			$.getJSON(context + "/updateComments", params, function(datas){
		var newDiv = makeCommentView(datas);
		var oldDiv = $("#c" + datas.seq);
		document.updateForm.reset(); // 폼 필드 클리어
		$("#comments").append($("#commentUpdate")); // 수정폼(div)을 바디로 이동
		$("#commentUpdate").hide(); // 수정폼 숨기기
		$(newDiv).replaceAll(oldDiv);	// 수정된 div 교체
			});
}
function confirmDeletion(seq){
	if(confirm("삭제하시겠습니까?")){
		var params = "seq=" + seq;
		$.getJSON(context + '/deleteComments', params, function(datas){
		$('#c'+datas.seq).remove();
		alert("삭제되었습니다.");
		
    	});
	}
}
</script>
</head>
<body>
<h1>게시글 조회</h1>
	번호 : ${board.seq }  <br>
	작성자 : ${board.writer } <br>
	작성일자 : ${board.regDate } <br>
	제목 : ${board.title} <br>
	내용 : ${board.content }
<a href="../updateBoardForm?seq=${board.seq }">수정</a>
<a href="../deleteBoard?seq=${board.seq }">삭제</a><p>
<a href="../getBoardList">목록으로</a>

<div id="comments">

<div id="commentList"></div>

<!-- 댓글등록시작 -->
<div id="commentAdd">
	<form action="" name="addForm">
	<input type="hidden" name="boardSeq" value="${board.seq}">
	이름: <input type="text" name="name" size="10"><br/>
	내용: <textarea name="content" cols="20" rows="2"></textarea><br/>
	<input type="button" value="등록" onclick="addComment()"/>
	</form>
</div>
<!-- 댓글등록끝 -->

<!-- 댓글수정폼시작 -->
<div id="commentUpdate" style="display:none">
	<form action="" name="updateForm">
	<input type="hidden" name="boardSeq" value="${board.seq}">
	<input type="hidden" name="seq" value=""/>
	이름: <input type="text" name="name" size="10"><br/>
	내용: <textarea name="content" cols="20" rows="2"></textarea><br/>
	<input type="button" value="등록" onclick="updateComment()"/>
	<input type="button" value="취소" onclick="cancelUpdate()"/>
	</form>
</div>
<!-- 댓글수정폼끝 -->

</div>
</body>
</html>