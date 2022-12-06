<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>selectCompensation</title>
</head>
<body>
	<form action="selectCompensation">
		<p>가입자명: ${CustomerName}</p>
		<p>전화번호: ${CustomerPhoneNum}</p>
		<p>사고내용: ${Content}</p>
		<p>보상금액: ${LiablityCost}</p>		
		<button type="submit" name=select value="compensation">지급</button>
		<button type="submit" name=select value="cancel">취소</button>
	</form>
</body>
</html>