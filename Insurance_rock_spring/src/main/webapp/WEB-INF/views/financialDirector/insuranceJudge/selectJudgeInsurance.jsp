<%@page import="com.mju.spring.dto.financialDirector.insuranceJudge.RegisterInsuranceDto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>selectJudgeInsurance</title>

</head>
<body>
	<p>심사 할 보험을 선택해주십시오.</p>
	<form action="selectJudgeInsurance">
		<table border="1">
			<thead>
				<tr>
					<th>보험명</th>
					<th>보험종류</th>
					<th>기준보험료</th>
				</tr>
			</thead>
			<tbody> 
				<c:forEach var="list" items="${RegisterInsuranceList}" varStatus="status">
					<tr>
						<td><input name="name" value="<c:out value="${list.insuranceName}"/>" readonly></td>
						<td><input name="type" value="<c:out value="${list.insuranceType}"/>" readonly></td>
						<td><c:out value="${list.standardFee}" /></td>
						<td><button type="submit">선택</button></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>

	</form>

</body>
</html>