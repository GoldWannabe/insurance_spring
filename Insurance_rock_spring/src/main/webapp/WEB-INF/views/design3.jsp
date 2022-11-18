<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<p>-----보험 Info-----</p>
	<p>보험 종류: ${InsuranceType}</p>
	<p>장기 여부: ${LongTerm}</p>
	<p>보험 이름: ${InsuranceName}</p>
	<p>특약: ${SpecialContract} </p>
	<p>가입 조건: ${ApplyCondition}</p>
	<p>보상 조건: ${CompensateCondition}</p>
	<p>설명: ${Explanation}</p>

확인을 누르시면 설계가 완료됩니다.
<button type="button" onclick="location.href='menu'">확인</button>


</body>
</html>