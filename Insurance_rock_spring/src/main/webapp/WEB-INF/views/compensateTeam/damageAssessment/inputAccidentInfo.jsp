<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>inputAccidentInfo</title>
</head>
<body>
	<form action="inputAccidentInfo">
		���¥: <input type="date" name="accidentDate" required><br>
		�����: <input type="text" name="content" required><br>
		�Ѻ��: <input type="number" name="totalCost" min="0" required><br>
		������: <input type="number" name="damagePer" min="0" max="100" required><br>
		��������: <input type="text" name="kindOfCost" min="0" required><br>
		å�Ӻ���: <input type="number" name="liablityRate" min="0" max="100" required><br>
		<button type="submit">��� �����ϱ�</button>
	</form>
</body>
</html>