<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>selectPaymentAmount</title>
</head>
	<body>
		<form action="selectPaymentAmount">
			<button type="submit" name="selectAmount" value="full">일시불(전부 납부)</button>
			<button type="submit" name="selectAmount" value="part">일부 납부</button>
			<button type="submit" name="selectAmount" value="cancel">취소</button>
		</form>
		
	</body>
</html>