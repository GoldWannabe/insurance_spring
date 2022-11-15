<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script>
function rtn(){
	if(confirm("다시쓰기를 하시겠습니까?")){
		f.reset();                 //페이지 초기화
		f.player_name.select();    //이름 블록
		
	} else return;
}
</script>
</head>
<body oncontextmenu="return false" onselectstart="return false"
	ondragstart="return false">
	
	<form action="design" >
		이름: <input type="text">	
		<button onclick="rtn();">예</button>
		<button onclick="rtn();">아니오</button>
	</form>
	
	<p>여기가 팝업 창입니다.</p>
</body>
</html>