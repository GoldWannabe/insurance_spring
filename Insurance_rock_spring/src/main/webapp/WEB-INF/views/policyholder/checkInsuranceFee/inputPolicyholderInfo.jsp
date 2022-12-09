<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>inputPolicyholderInfo</title>
</head>
<body>
	<form action="inputPolicyholderInfo">
		가입자 이름: <input type="text" name="customerName" required><br>
		연락처: <input type="text" name="customerPhoneNum" required><br>
		<button type="submit" name="selectPayment" value="getPayment">납부 금액</button>
		<button type="submit" name="selectPayment" value="cancel">취소</button>
	</form>
</body>
</html>