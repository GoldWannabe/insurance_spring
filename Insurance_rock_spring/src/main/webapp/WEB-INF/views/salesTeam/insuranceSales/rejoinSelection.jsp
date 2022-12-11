<%@page import="com.mju.spring.entity.Contract"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>rejoinSelection</title>
</head>
<body>
<p>재가입할 계약을 선택해주십시오. </p>
<form action="rejoinSelection">
	<table border="1">
		<thead>
			<tr>
				<th>가입자 이름</th>
				<th>연락처</th>
				<th>보험이름</th>
				<th>납부방식</th>
				<th>보험료</th>
				<th>담보액</th>
				<th>가입기간</th>
				<th>탈락사유</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var = "list" items = "${failContractList}" varStatus="status">
				<tr>
					<td><c:out value="${list.customerName}"/></td>
					<td><c:out value="${list.customerPhoneNum}"/></td>
					<td><c:out value="${list.insuranceName}"/></td>
					<td><c:out value="${list.paymentCycle}"/></td>
					<td><c:out value="${list.insuranceFee}"/></td>
					<td><c:out value="${list.securityFee}"/></td>
					<td><c:out value="${list.period}"/></td>
					<td><c:out value="${list.reason}"/></td>
					<td><button type="submit" name = "index"  value="<c:out value="${status.index}"/>">재가입</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</form>

</body>
</html>