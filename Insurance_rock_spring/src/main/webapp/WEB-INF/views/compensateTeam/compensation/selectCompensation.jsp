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
		<p>�����ڸ�: ${Accident.customerName }</p>
		<p>��ȭ��ȣ: ${Accident.customerPhoneNum}</p>
		<p>�����: ${Accident.content}</p>
		<p>����ݾ�: ${Accident.liablityCost}</p>		
		<button type="submit" name=select value="compensation">����</button>
		<button type="submit" name=select value="cancel">���</button>
	</form>
</body>
</html>