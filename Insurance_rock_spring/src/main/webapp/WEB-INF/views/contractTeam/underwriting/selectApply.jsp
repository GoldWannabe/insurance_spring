<%@page import="com.mju.spring.dto.contractTeam.Underwriting.ApplyContractDto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>selectApply</title>

</head>
<body>
	<p>심사 할 보험을 선택해주십시오.</p>
	<form action="selectApply">
		<table border="1">
			<thead>
				<tr>
					<th>계약번호(순번)</th>
					<th>보험이름</th>
					<th>가입자명</th>
					<th>연락처</th>
					<th>담보액</th>
					<th>보험료</th>
					<th>가입기간(달)</th>
				</tr>
			</thead>
			<tbody> 
				<c:forEach var="list" items="${ApplyContractList}" varStatus="status">
					<tr>
						
						<td><input name="index" value="<c:out value="${status.index}"/>" readonly></td>
						
						<td><c:out value="${list.insuranceName}" /></td>
						<td><c:out value="${list.customerName}" /></td>
						<td><c:out value="${list.customerPhoneNum}" /></td>
						<td><c:out value="${list.securityFee}" /></td>
						<td><c:out value="${list.insuranceFee}" /></td>
						<td><c:out value="${list.period}" /></td>
						<td><button type="submit" name="selectVerify" value="verify">검증</button></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
		<button type="submit" name="selectVerify" value="cancel">취소</button>
	</form>

</body>
</html>