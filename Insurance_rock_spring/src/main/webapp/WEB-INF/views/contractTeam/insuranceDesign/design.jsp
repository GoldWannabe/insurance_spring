<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>design</title>
</head>
<body>
	<form action="inputTypeAndTerm">
		장기여부 <br> <input type="radio" name="longTerm" value="true" />장기
		<input type="radio" name="longTerm" value="false" />단기 <br> 보험
		종류 <br> <input type="radio" name="InsuranceType" value="general" />일반보험 <input
			type="radio" name="InsuranceType" value="house" />주택보험 <br> 
			<input type="submit" value="다음"/>
	</form>
</body>
</html>