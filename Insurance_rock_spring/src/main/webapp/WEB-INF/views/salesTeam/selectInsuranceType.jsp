<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>insuranceType</title>

</head>
<body>
<p>가입하실 보험을 선택해주십시오. </p>
	<form action="insuranceType">
		<p> 보험 종류 </p>
		
		<input type="radio" name="InsuranceType" value="general" />일반보험 
		<input type="radio" name="InsuranceType" value="house" />주택보험 <br> 
		
		<input type="submit" value="Next"/>
		<input type="button" onclick="location.href='menu'">취소</button>
	</form>
	
</body>	
</html>