<%@page import="com.mju.spring.dto.policyholder.feePayment.DuePaymentDto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>selectDuePayment</title>

</head>
<body>
	<p>심사 할 보험을 선택해주십시오.</p>
	<form action="selectDuePayment">
		<table border="1">
			<thead>
				<tr>
					<th>보험 이름</th>
					<th>일시불 여부</th>
					<th>보험료</th>
					<th>미납액</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="list" items="${DuePaymentList}" varStatus="status">
					<tr>
						<td><c:out value="${list.insuranceName}" /></td>
						<td><c:out value="${list.fullPayment}" /></td>
						<td><c:out value="${list.insuranceFee}" /></td>
						<td><c:out value="${list.unpaidFee}" /></td>
						<td><button type="submit" name="selectPayOrRecord" value="payInsuranceFee <c:out value="${status.index}"/>">납부 방법</button></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
		<button type="submit" name="selectPayOrRecord" value="showPaymentRecord">납부 기록</button>
		<button type="submit" name="selectPayOrRecord" value="showProvisionRecord">지급 내역</button>
		<button type="submit" name="selectPayOrRecord" value="cancel">취소</button>
	</form>

</body>
</html>