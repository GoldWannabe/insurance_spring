<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>selectUnderwrite</title>

</head>
<body>

	<form action="selectUnderwrite">
		<p>가입 요청 보험료: ${ApplyContract.insuranceFee}</p>
		<p>기준 보험료: ${ApplyContract.standardFee}</p>
		<p>담보액: ${ApplyContract.securityFee}</p>
		<p>가입기간: ${ApplyContract.period}</p>
		<p>납부방식(주기): ${ApplyContract.paymentCycle}</p>
		<p>가입 조건: ${ApplyContract.applyCondition}</p>
		<p>재질: ${Rank.material}</p>
		<p>용도: ${Rank.purpose}</p>
		<p>크기(평수): ${Rank.scale}</p>
		<p>고층여부: ${Rank.height}</p>
		<p>소방시설 점수: ${Rank.fireFacilities}</p>
		<p>주변시설 점수: ${Rank.surroundingFacilities}</p>
		<p>최종등급: ${ApplyContract.totalRank}</p>

		<div>
			<button type="submit" name=underwriteMenu value=apply>신규</button>
			<button type="submit" name=underwriteMenu value=renew>갱신</button>
			<button type="submit" name=underwriteMenu value=cancel>취소</button>
		</div>
	</form>

</body>
</html>
