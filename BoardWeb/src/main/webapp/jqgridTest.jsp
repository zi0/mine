<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" media="screen" href="./scripts/jquery-ui.min.css" />
<link rel="stylesheet" type="text/css" media="screen" href="./resources/jqGrid/css/ui.jqgrid.css" />

<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> -->
<script src="./resources/jqGrid/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="./resources/jqGrid/js/i18n/grid.locale-en.js" type="text/javascript"></script>
<script src="./resources/jqGrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>

<script>
	$(function(){
		 $("#list").jqGrid({
			 url: "getBoardListAjax",
			 datatype: "json",
			 colNames: ["no", "제목", "작성자", "작성일자", "조회수"],
		     colModel: [
		            { name: "no", width: 55 },
		            { name: "title", width: 280, align: "right" },
		            { name: "writer", width: 90 },
		            { name: "regDate", width: 80, align: "right" },
		            { name: "cnt", width: 80, align: "right" },
		        ],
		        pager: "#pager",
		}); 
	});
</script>
</head>
<body>
	<table id="list">
		<tr>
			<td></td>
		</tr>
	</table>
	<div id="pager"></div>
</body>
</html>