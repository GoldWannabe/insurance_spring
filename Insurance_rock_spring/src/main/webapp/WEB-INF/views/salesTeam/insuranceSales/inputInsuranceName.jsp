<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>InsuranceSearchScrean</title>

</head>
<body>

	<table border="1">
		<thead>
			<tr >
				<th>�����</th>
				<th>���� ����</th>
			</tr>
		</thead>

		<tbody>
			<tr th:each = " list: ${tableList}">
				<td><span th:text = "${list.name}"></span></td>
				<td><span th:text = "${list.type}"></span></td>
			</tr>
		</tbody>
	</table>
	
	<form action="customerInfo">
		<p>�˻��ϰ��� �ϴ� ���� �̸��� �Է����ֽʽÿ�.</p>

		���� �̸�: <input type="text" name="inusranceName">
		<button type="submit" name="search" value="search" />
		�˻�
		</button>
	</form>
</body>
</html>