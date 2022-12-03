<%@page import="com.mju.spring.entity.Contract"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>selectRankAndBesides</title>

</head>
<body>
	<p>갱신 혹은 해지할 계약을 선택해주십시오.</p>
	<form action="selectRankAndBesides">
		<div>
			<button type="submit" name="renew" value="rankRenew">등급 갱신</button>
			<button type="submit" name="renew" value="BesidesRenew">등급외 계약내용 갱신</button>
		</div>

	</form>

</body>
</html>