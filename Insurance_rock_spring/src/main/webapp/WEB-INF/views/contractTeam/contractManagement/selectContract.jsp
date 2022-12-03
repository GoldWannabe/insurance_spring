<%@page import="com.mju.spring.entity.Contract"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>selectContract</title>

</head>
<body>
	<p>갱신 혹은 해지할 계약을 선택해주십시오.</p>
	<form action="selectContract">
		<table border="1">
			<thead>
				<tr>
					<th>고객명</th>
					<th>전화번호</th>
					<th>보험명</th>
					<th>납부방식(주기)</th>
					<th>보험료</th>
					<th>미납액</th>
					<th>담보액</th>
					<th>지급액</th>
					<th>가입일</th>
					<th>만료일</th>
					<th>비고</th>
					
				</tr>
			</thead>
			<tbody> 
															
				<c:forEach var="list" items="${ContractList}" varStatus="status">
					<tr>
						<td><c:out value="${list.customerName}"/> </td>
						<td><c:out value="${list.customerPhoneNum}"/></td>						
						<td><c:out value="${list.insuranceName}"/></td>
						<td><c:out value="${list.paymentCycle}"/></td>
						<td><c:out value="${list.insuranceFee}"/></td>
						<td><c:out value="${list.unpaidFee}"/></td>
						<td><c:out value="${list.securityFee}"/></td>
						<td><c:out value="${list.provisionFee}"/></td>
						<td><c:out value="${list.startDate}"/></td>
						<td><c:out value="${list.endDate}"/></td>
						<td><input type="hidden" name="contractID" value="<c:out value="${list.contractID}"/>">
						<input type="hidden" name="customerID" value="<c:out value="${list.customerID}"/>">
						<input type="hidden" name="insuranceID" value="<c:out value="${list.insuranceID}"/>">
						</td>
						
						<td><button type="submit">선택</button></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>

	</form>

</body>
</html>