<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<script>

	function UseStandardRate(form) {
		  if (confirm('���� ������ ����Ͻðڽ��ϱ�?')==1) {
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
		<p>���� ����: ${InsuranceType}</p>
		<p>��� ����: ${LongTerm}</p>
		<br> �̸�: <input type="text" name="insuranceName"> <br>
		Ư��: <input type="text" name="specialContract"> <br>
		��������: <input type="text" name="applyCondition"> <br>
		���� ����: <input type="text" name="compensateCondition"/> <br>
		����: <input type="text" name="explanation"> <br>
		<input  type="button" value="����" onclick="UseStandardRate(this.form);"/>
	</form>
		

</body>
</html>