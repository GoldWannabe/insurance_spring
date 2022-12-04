<%@page import="com.mju.spring.entity.Contract"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>selectRenewAndRenewCancel</title>

</head>
<body>
	<p>-----사고 이력 -----</p>
	<form action="selectRenewAndRenewCancel">
		<table border="1">
			<thead>
				<tr>
					<th>계약ID</th>
					<th>사고ID</th>	
				</tr>
			</thead>
			<tbody> 									
				<c:forEach var="list" items="${ContractAccidentList}" varStatus="status">
					<tr>
						<td><c:out value="${list.contractID}"/> </td>
						<td><c:out value="${list.accidentID}"/></td>						
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<p>${NotAccidentHistory}</p>
		<div>
			<button type="submit" name="RenewMenu" value="renew">계약 갱신</button>
			<button type="submit" name="RenewMenu" value="renewCancel">계약 해지</button>
			<button type="submit" name="RenewMenu" value="cancel">취소</button>
		</div>
	</form>

</body>
</html>