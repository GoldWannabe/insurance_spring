<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>selectCompensation</title>
</head>
<body>
	<form action="selectCompensation">
		<p>가입자명: ${Accident.customerName }</p>
		<p>전화번호: ${Accident.customerPhoneNum}</p>
		<p>사고내용: ${Accident.content}</p>
		<p>보상금액: ${Accident.liablityCost}</p>		
		<button type="submit" name=select value="compensation">지급</button>
		<button type="submit" name=select value="cancel">취소</button>
	</form>
</body>
</html>