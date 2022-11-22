
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<tr>
				<th>�����</th>
				<th>���� ����</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="list" items="${InsuranceList}" varStatus="status">
				<tr>
					<td><c:out value="${list.insuranceName}" /></td>
					<td><c:out value="${list.insuranceType}" /></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>

	<form action="inputInsuranceName">
		<p>�����ϰ��� �ϴ� ���� �̸��� �Է����ֽʽÿ�.</p>

		���� �̸�: <input type="text" name="inusranceName">
		<button type="submit" name="search" value="search">�˻�</button>
	</form>
</body>
</html>