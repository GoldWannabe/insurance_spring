<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>checkAccidentInfo</title>
	<script>
		alert('사고정보가 추가되었습니다');	
	</script>
</head>
<body>

	<p>-----사고 접수 내역-------</p>
	<form action="checkAccidentInfo" >
		<p>가입자명: ${Accident.customerName }</p>
		<p>전화번호: ${Accident.customerPhoneNum}</p>
		<p>사고날짜: ${Accident.accidentDate}</p>
		<p>사고내용: ${Accident.content}</p>
		<p>총비용: ${Accident.totalCost}</p>
		<p>손해율: ${Accident.damagePer}</p>
		<p>보상종류: ${Accident.kindOfCost}</p>
		<p>지급여부: ${Accident.payCompleted}</p>
		<p>책임비율: ${Accident.liablityRate}</p>
		<p>보상금액: ${Accident.liablityCost}</p>
		<button type="submit">메인화면으로</button>
	</form>

</body>
</html>
