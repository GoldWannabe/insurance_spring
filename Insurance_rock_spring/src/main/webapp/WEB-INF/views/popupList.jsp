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
		�̸�: <input type="text">	
		<button onclick="isWindowOpen();">��</button>
		<button onclick="isWindowOpen();">�ƴϿ�</button>
	</form>
	
	<p>���Ⱑ �˾� â�Դϴ�.</p>
</body>
</html>