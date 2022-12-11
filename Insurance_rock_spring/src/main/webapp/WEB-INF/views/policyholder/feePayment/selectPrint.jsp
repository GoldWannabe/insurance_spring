<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>showResult</title>
</head>
<body>
	<form action="selectPrint">
	<p>${CheckPrint} </p>
	<button type="submit" name="printPayment" value="print">출력</button>
	<button type="submit" name="printPayment" value="cancel">취소</button>
	</form>
</body>
</html>