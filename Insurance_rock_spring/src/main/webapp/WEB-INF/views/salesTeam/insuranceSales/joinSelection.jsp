<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>insuranceType</title>

</head>
<body>
<p>��ȸ�� �����Դϴ�.</p>
	<form action="joinSelection">
		����� : ${InsuranceName} <br>
		���� ����: ${InsuranceType} <br>
		���غ���� : ${StandardFee}<br>
		Ư��: ${SpecialContract}<br>
		��⿩�� : ${LongTerm}<br>
		��������: ${ApplyCondition}<br>
		�������� : ${CompensateCondition}<br>
		����: ${Explanation}<br>
		���� : [1���, 2���, 3���] [ ${PremiumRate[0]}, ${PremiumRate[1]}, ${PremiumRate[2]} ]<br>
		${InsuranceName} ���迡 �����Ͻðڽ��ϱ�?
		<button type="submit" name = "join" value="join">����</button>
		<button type="submit" name = "join" value="reJoin">�簡�� ��û</button>
		<button type="submit" name = "join" value="cancel">���</button>
	</form>
	
</body>	
</html>