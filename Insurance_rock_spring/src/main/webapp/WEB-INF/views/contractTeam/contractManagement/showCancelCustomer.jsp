<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>showResult</title>
	<script>
		alert('보험 [ ${InsuranceName} ] 삭제 후 ( 이름: ${CustomerName} , 연락처:${CustomerPhoneNum} ) 고객님이 계약 중인 보험이 없어 고객 데이터를 정상적으로 삭제했습니다.');	
	</script>
</head>
<body>
	<form action="showResult">
	<button type="submit">메인화면으로</button>
	</form>
</body>
</html>