<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>insuranceType</title>

</head>
<body>
<p>�����Ͻ� ������ �������ֽʽÿ�. </p>
	<form action="insuranceType">
		<p> ���� ���� </p>
		
		<input type="radio" name="InsuranceType" value="general" />�Ϲݺ��� 
		<input type="radio" name="InsuranceType" value="house" />���ú��� <br> 
		
		<input type="submit" value="Next"/>
		<input type="button" onclick="location.href='menu'">���</button>
	</form>
	
</body>	
</html>