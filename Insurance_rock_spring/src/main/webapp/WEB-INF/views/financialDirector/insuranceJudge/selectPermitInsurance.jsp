<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>selectPermitInsurance</title>

</head>
<body>
	<br>

	<form action="selectPermitInsurance">

		<p>보험 이름: ${Insurance.insuranceName}</p>
		<p>보험 종류: ${Insurance.insuranceType}</p>
		<p>기준 보험료: ${Insurance.standardFee}</p>
		<p>특약: ${Insurance.specialContract}</p>
		<p>장기 여부: ${Insurance.longTerm}</p>
		<p>가입 조건: ${Insurance.applyCondition}</p>
		<p>보상 조건: ${Insurance.compensateCondition}</p>
		<p>설명: ${Insurance.explanation}</p>
		<p>요율: [1등급, 2등급, 3등급 ] [ ${Insurance.premiumRate[0]}, ${Insurance.premiumRate[1]}, ${Insurance.premiumRate[2]} ]</p>


		<div>
			<button type="submit" name="selectPerit" value="permit">승인</button>
			<button type="submit" name="selectPerit" value="notPermit">비승인</button>
			<button type="submit" name="selectPerit" value="defer">보류</button>
		</div>
	</form>


</body>
</html>