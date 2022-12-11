<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>manageCustomer</title>

</head>
<body>
<p>조회된 보험입니다.</p>
	<form action="manageCustomer">
		가입자명 :  <br>
		주민/사업자번호:  <br>
		연락처 : <br>
		주소: <br>
		성별 : <br>
		계약번호: <br>
		가입보험 개수 : <br>
		은행명: <br>
		계좌번호 :<br>
		<button type="submit" name = "manage" value="update">수정</button>
		<button type="submit" name = "manage" value="add">추가</button>
		<button type="submit" name = "manage" value="delete">삭제</button>
	</form>
	
</body>	
</html>