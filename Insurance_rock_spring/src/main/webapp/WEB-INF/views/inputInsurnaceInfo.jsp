<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<script>

	function rtn() {
		if (confirm("기존 요율을 사용하시겠습니까?")) {
			//location.replace("design")
			return true;
		} else
			return false;
	}
</script>

</head>
<body>
	<form name="rateChoice" action="insuranceInfo" onSubmit="rtn();">
		<p>보험 종류: ${InsuranceType}</p>
		<p>장기 여부: ${LongTerm}</p>
		<br> 이름: <input type="text" name="inusranceName"> <br>
		특약: <input type="text" name="specialContract"> <br>
		가입조건: <input type="text" name="applyCondition"> <br>
		보상 조건: <input type="text" name="compensateCondition"/> <br>
		설명: <input type="text" name="explanation"> <br>
		<input  type="submit" value="Next" />
	</form>
</body>
</html>