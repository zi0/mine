<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>사원수(구글차트)</title>
<script src="//www.google.com/jsapi"></script>
<script src="./scripts/jquery-3.3.1.min.js"></script>
<script src="./scripts/json.min.js"></script>
<script>
	var options = {
		title : '부서별 사원수',
		width : 400,
		height : 500
	};
	google.load('visualization', '1.0', {
		'packages' : [ 'corechart' ]
	});
	google.setOnLoadCallback(function() {
		//차트에 넣을 data를 ajax 요청해서 가져옴
		$.ajax({
			url : "./getChartData",
			method : "post",
			type : "json",
			success : function(data) {
				//ajax결과를 chart에 맞는 data 형태로 가공
				var chartData = [];
				chartData.push([ '사원명', '사원수' ])
				for (i = 0; i < data.length; i++) {
					var subarr = [ data[i].name, parseInt(data[i].cnt) ];
					chartData.push(subarr);
				}
				//챠트 그리기
				var chart = new google.visualization.ColumnChart(document.querySelector('#chart_div'));
				chart.draw(google.visualization.arrayToDataTable(chartData), options);
			}
		});
	});
</script>
</head>
<body>
	<div id="chart_div"></div>
</body>
</html>