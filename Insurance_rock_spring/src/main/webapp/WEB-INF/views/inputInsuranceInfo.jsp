<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<script>

	function rtn() {
		if (confirm("���� ������ ����Ͻðڽ��ϱ�?")) {
			//location.replace("design")
			return true;
		} else
			return false;
	}
</script>

</head>
<body>
	<form name="rateChoice" action="insuranceInfo" onSubmit="rtn();">
		<p>���� ����: ${InsuranceType}</p>
		<p>��� ����: ${LongTerm}</p>
		<br> �̸�: <input type="text" name="inusranceName"> <br>
		Ư��: <input type="text" name="specialContract"> <br>
		��������: <input type="text" name="applyCondition"> <br>
		���� ����: <input type="text" name="compensateCondition"/> <br>
		����: <input type="text" name="explanation"> <br>
		<input  type="submit" value="Next" />
	</form>
</body>
</html>