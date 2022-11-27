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
		사고날짜: <input type="date" name="accidentDate" required><br>
		사고내용: <input type="text" name="content" required><br>
		총비용: <input type="number" name="totalCost" min="0" required><br>
		손해율: <input type="number" name="damagePer" min="0" max="100" required><br>
		보상종류: <input type="text" name="kindOfCost" min="0" required><br>
		책임비율: <input type="number" name="liablityRate" min="0" max="100" required><br>
		<button type="submit">사고 접수하기</button>
	</form>
</body>
</html>