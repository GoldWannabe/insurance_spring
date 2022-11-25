<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<script>

	function UseStandardRate(form) {
		  if (confirm('기존 요율을 사용하시겠습니까?')==1) {
			form.action = "inputInsuranceInfo";
			form.submit();
		  } else {
			form.action = "rateScrean";
			form.submit();
		  }
		  
	}
</script>

</head>
<body>
	<form>
		<p>보험 종류: ${InsuranceType}</p>
		<p>장기 여부: ${LongTerm}</p>
		<br> 이름: <input type="text" name="insuranceName"> <br>
		특약: <input type="text" name="specialContract"> <br>
		가입조건: <input type="text" name="applyCondition"> <br>
		보상 조건: <input type="text" name="compensateCondition"/> <br>
		설명: <input type="text" name="explanation"> <br>
		<input  type="button" value="다음" onclick="UseStandardRate(this.form);"/>
	</form>
		

</body>
</html>