<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function() {
		url = "popupList"
		$.ajax({
			type : "POST",
			url : popupList,
			data : $("#myForm").serialize(),
			success : function(data) {
			},
			error : function(request, status, msg) {
			}
		});
	});
	
	function isWindowOpen(){
		
		window.close()	
	}
	
</script>
</head>
<body oncontextmenu="return false" onselectstart="return false"
	ondragstart="return false">
	
	<form >
		이름: <input type="text">	
		<button onclick="isWindowOpen();">예</button>
		<button onclick="isWindowOpen();">아니오</button>
	</form>
	
	<p>여기가 팝업 창입니다.</p>
</body>
</html>