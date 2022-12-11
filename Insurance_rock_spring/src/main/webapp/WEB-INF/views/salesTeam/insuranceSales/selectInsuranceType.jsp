<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>insuranceType</title>

</head>
<body>
<p>가입하실 보험을 선택 해주십시오. </p>
	
	<form action="selectInsuranceType">
		<input type="radio" name="insuranceType" value="general" checked required/>일반보험 
		<input type="radio" name="insuranceType" value="house" />주택보험 <br> 
		
		<button type="submit" name ="next" value="next" >다음</button>
		<button type="submit" name="next" value="cancellation">취소 </button>
	</form>
	
</body>	
</html>