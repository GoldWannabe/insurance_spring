<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>InsuranceSearchScrean</title>

</head>
<body>
	<form action="rejoin">
		<p>재가입할 회원을 검색해주세요</p>

		고객 이름: <input type="text" name="customerName"> <br> 전화번호:
		<input type="text" name="phoneNum"><br>
		<button type="submit" name="search" value="search">검색</button>
		<button type="submit" name="search" value="cancel">취소</button>
	</form>

</body>
</html>