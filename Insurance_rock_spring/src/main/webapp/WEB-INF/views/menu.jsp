<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Menu</title>
<script>
function rtn(){
	if(confirm("�ٽþ��⸦ �Ͻðڽ��ϱ�?")){
		//location.replace("design")
		return true;
	} else return false;
}
</script>
</head>
<body>

	<div>
	<form action="design" >
		<button type="submit" onclick="rtn();">����</button>
		<button type="button">�μ��ɻ�</button>
		<button type="button">������</button></form>
	</div>
	
</body>
</html>