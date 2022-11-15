<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form action="">
		<p>보험 종류: ${InsuranceType}</p>
		<p>장기 여부: ${LongTerm}</p>
		<br> 이름: <input type="text" name="inusranceName"> <br> 특약: <input
			type="text" name=""> <br> 가입조건: <input type="text"> <br>
		보상 조건: <input type="text"> <br> 설명: <input type="text">
		<input type="submit" value="Next"/>
	</form>
</body>
</html>