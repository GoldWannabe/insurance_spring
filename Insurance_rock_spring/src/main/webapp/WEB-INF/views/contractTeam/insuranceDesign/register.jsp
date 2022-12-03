<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>insuranceInfo</title>

</head>
<body>

	<form action = "register">

		<p>-----보험 Info-----</p>
		<p>보험 이름: ${InsuranceName}</p>
		<p>보험 종류: ${InsuranceType}</p>
		<p>기준 보험료: ${StandardFee}</p>		
		<p>특약: ${SpecialContract}</p>
		<p>장기 여부: ${LongTerm}</p>
		<p>가입 조건: ${ApplyCondition}</p>
		<p>보상 조건: ${CompensateCondition}</p>
		<p>설명: ${Explanation}</p>
		<p>요율: [1등급, 2등급, 3등급 ] [ ${PremiumRate[0]}, ${PremiumRate[1]}, ${PremiumRate[2]} ]</p>
		
		<p>등록하시겠습니까?</p>
		
		확인을 누르시면 설계가 완료됩니다.

		<button  type="submit" name="register" value="register">등록</button>
		<button type="submit" name="register" value="cancel">취소</button> 
	</form>

</body>
</html>