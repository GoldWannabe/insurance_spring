<%@page import="com.mju.spring.dto.policyholder.feePayment.ProvisionDto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>showProvisionRecord</title>

</head>
<body>
	<p>심사 할 보험을 선택해주십시오.</p>
	<form action="showProvisionRecord">
		<table border="1">
			<thead>
				<tr>
					<th>보험이름</th>
					<th>보험종류</th>
					<th>장기여부</th>
					<th>은행명</th>
					<th>계좌번호</th>
					<th>보상금액</th>
					<th>보상일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="list" items="${ProvisionList}" varStatus="status">
					<tr>
						<td><c:out value="${list.insuranceName}" /></td>
						<td><c:out value="${list.insuranceType}" /></td>
						<td><c:out value="${list.longTerm}" /></td>
						<td><c:out value="${list.bankName}" /></td>
						<td><c:out value="${list.accountNum}" /></td>
						<td><c:out value="${list.compensation}" /></td>
						<td><c:out value="${list.compensationDate}" /></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
		<button type="submit" name="selectCheck" value="check">확인</button>
	</form>

</body>
</html>