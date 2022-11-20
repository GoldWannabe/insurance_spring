<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>insuranceType</title>

</head>
<body>
<p>조회된 보험입니다.</p>
	<form action="joinInsurance">
		보험명 : ${InsuranceName} <br>
		보험 종류: ${InsuranceType} <br>
		기준보험료 : ${StandardFee}<br>
		특약: ${SpecialContract}<br>
		장기여부 : ${LongTerm}<br>
		가입조건: ${ApplyCondition}<br>
		보상조건 : ${CompensateCondition}<br>
		설명: ${Explanation}<br>
		요율 : [1등급, 2등급, 3등급] [${PremiumRate}]<br>
		${InsuranceName} 보험에 가입하시겠습니까?
		<button type="submit" name = "join" value="insuranceJoin">가입</button>
		<button type="submit" name = "join" value="insuranceReJoin">재가입 신청</button>
		<button type="submit" name = "join" value="cancellation">취소</button>
	</form>
	
</body>	
</html>