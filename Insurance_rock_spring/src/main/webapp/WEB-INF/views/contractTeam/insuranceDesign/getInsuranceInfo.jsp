<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>insuranceInfo</title>
</head>
<body>
	<form action="register">
		<p>-----���� Info-----</p>
		<p>���� ����: ${InsuranceType}</p>
		<p>��� ����: ${LongTerm}</p>
		<p>���� �̸�: ${InsuranceName}</p>
		<p>Ư��: ${SpecialContract}</p>
		<p>���� ����: ${ApplyCondition}</p>
		<p>���� ����: ${CompensateCondition}</p>
		<p>����: ${Explanation}</p>

		Ȯ���� �����ø� ���谡 �Ϸ�˴ϴ�.

		<button type="submit" name="menu" value="menu">Ȯ��</button>
	</form>
</body>
</html>