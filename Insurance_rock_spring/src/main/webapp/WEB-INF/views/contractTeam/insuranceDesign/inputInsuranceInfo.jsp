<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<script>

	function UseStandardRate(check) {
		  if (confirm('기존 요율을 사용하시겠습니까?')==1) {
			check.value = "confirm";
		  } else {
			check.value = "cancel";
		  }
		  
	}
</script>

</head>
<body>
	<form action = inputInsuranceInfo>
		<p>보험 종류: ${InsuranceType}</p>
		<p>장기 여부: ${LongTerm}</p>
		<br> 이름: <input type="text" name="insuranceName" required/> <br>
		특약: <input type="text" name="specialContract" required/> <br>
		가입조건: <input type="text" name="applyCondition" required/> <br>
		보상 조건: <input type="text" name="compensateCondition" required/> <br>
		설명: <input type="text" name="explanation" required/> <br>
		<input name = "check" type="submit" value="다음" onclick="UseStandardRate(this);"/>
	</form>
</body>
</html>