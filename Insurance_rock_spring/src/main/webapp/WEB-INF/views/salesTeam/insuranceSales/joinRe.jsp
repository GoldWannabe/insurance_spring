<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>InsuranceSearchScrean</title>

</head>
<body>
	<form action="rejoin">
		<p>�簡���� ȸ���� �˻����ּ���</p>

		�� �̸�: <input type="text" name="customerName"> <br>
		��ȭ��ȣ: <input type="text" name="phoneNum"><br>
		<button type="submit" name="search" value="search" >
		�˻�
		</button>
	</form>

	<table border="1">
		<thead>
			<tr >
				<th>������ �̸�</th>
				<th>����ó</th>
				<th>�����̸�</th>
				<th>���ι��</th>
				<th>�����</th>
				<th>�㺸��</th>
				<th>���ԱⰣ</th>
				<th>Ż������</th>
			</tr>
		</thead>

		<tbody>
			<tr th:each = "list: ${tableList}">
				<td><span th:text = "${list.customerName}"></span></td>
				<td><span th:text = "${list.phoneNum}"></span></td>
				<td><span th:text = "${list.insuranceName}"></span></td>
				<td><span th:text = "${list.paymentCycle}"></span></td>
				<td><span th:text = "${list.insuranceFee}"></span></td>
				<td><span th:text = "${list.securityFee}"></span></td>
				<td><span th:text = "${list.period}"></span></td>
				<td><span th:text = "${list.faliContract,}"></span></td>
			</tr>
		</tbody>
	</table>
	
	
</body>
</html>