<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Menu</title>
<script>
function rtn(){
	if(confirm("다시쓰기를 하시겠습니까?")){
		//location.replace("design")
		return true;
	} else return false;
}
</script>
</head>
<body>

	<div>
	<form action="design" >
		<button type="submit" onclick="rtn();">설계</button>
		<button type="button">인수심사</button>
		<button type="button">계약관리</button></form>
	</div>
	
</body>
</html>