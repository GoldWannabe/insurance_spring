<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>insuranceInfo</title>

	<script>
	function registerCompletion(form) {
		alert("���� ����� �Ϸ�Ǿ����ϴ�.");
			form.action = "register";
			form.submit();
		  
	}
	</script>


</head>
<body>

	<form action = "register" onsubmit="alert('�ӽ����� �Ǿ����ϴ�.');">

		<p>-----���� Info-----</p>
		<p>���� �̸�: ${InsuranceName}</p>
		<p>���� ����: ${InsuranceType}</p>
		<p>���� �����: ${StandardFee}</p>		
		<p>Ư��: ${SpecialContract}</p>
		<p>��� ����: ${LongTerm}</p>
		<p>���� ����: ${ApplyCondition}</p>
		<p>���� ����: ${CompensateCondition}</p>
		<p>����: ${Explanation}</p>
		<p>����: [1���, 2���, 3��� ] [ ${PremiumRate[0]}, ${PremiumRate[1]}, ${PremiumRate[2]} ]</p>
		
		<p>����Ͻðڽ��ϱ�?</p>
		
		Ȯ���� �����ø� ���谡 �Ϸ�˴ϴ�.

		<input  type="button" value="���" onclick="UseStandardRate(this.form);"/>
		<button type="submit" name="register" value="cancel">���</button> 
	</form>

</body>
</html>