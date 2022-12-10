<%@page import="com.mju.spring.dto.policyholder.feePayment.PaymentDto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>showPaymentRecord</title>

</head>
<body>
	<p>심사 할 보험을 선택해주십시오.</p>
	<form action="showPaymentRecord">
		<table border="1">
			<thead>
				<tr>
					<th>가입자명</th>
					<th>연락처</th>
					<th>보험이름</th>
					<th>보험종류</th>
					<th>카드사/은행명</th>
					<th>카드/계좌번호</th>
					<th>납부금액</th>
					<th>납부일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="list" items="${PaymentList}" varStatus="status">
					<tr>
						<td><c:out value="${list.customerName}" /></td>
						<td><c:out value="${list.customerPhoneNum}" /></td>
						<td><c:out value="${list.insuranceName}" /></td>
						<td><c:out value="${list.insuranceType}" /></td>
						<td><c:out value="${list.cardOrBankName}" /></td>
						<td><c:out value="${list.accountNum}" /></td>
						<td><c:out value="${list.insuranceFee}" /></td>
						<td><c:out value="${list.paidDate}" /></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
		<button type="submit" name="selectCheck" value="check">확인</button>
	</form>

</body>
</html>