<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<script>

	function UseStandardRate(check) {
		  if (confirm('���� ������ ����Ͻðڽ��ϱ�?')==1) {
			check.value = "confirm";
		  } else {
			check.value = "cancel";
		  }
		  
	}
</script>

</head>
<body>
	<form action = inputInsuranceInfo>
		<p>���� ����: ${InsuranceType}</p>
		<p>��� ����: ${LongTerm}</p>
		<br> �̸�: <input type="text" name="insuranceName"> <br>
		Ư��: <input type="text" name="specialContract"> <br>
		��������: <input type="text" name="applyCondition"> <br>
		���� ����: <input type="text" name="compensateCondition"/> <br>
		����: <input type="text" name="explanation"> <br>
		<input name = "check" type="submit" value="����" onclick="UseStandardRate(this);"/>
	</form>
</body>
</html>