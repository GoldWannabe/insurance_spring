<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>selectApplyPermit</title>

</head>
<body>
	<p>원하시는 메뉴에 해당하는 버튼을 눌러주세요.</p>
	<br>

	<form action="selectApplyPermit">

		<p>가입 요청 보험료: ${Insurance.insuranceName}</p>
		<p>기준 보험료: ${Insurance.standardFee}</p>
		<p>담보액: ${Insurance.specialContract}</p>
		<p>등급: ${Insurance.longTerm}</p>
		<p>가입기간: ${Insurance.insuranceType}</p>
		<p>납부방식: ${Insurance.applyCondition}</p>
		<p>가입 조건: ${Insurance.compensateCondition}</p>
		

		<div>
			<button type="submit" name="selectPerit" value="permit">계약</button>
			<button type="submit" name="selectPerit" value="notPermit">반려</button>
			<button type="submit" name="selectPerit" value="cancel">취소</button>
		</div>
	</form>


</body>
</html>