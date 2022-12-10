<!--<%@page import="com.mju.spring.entity.Accident"%>-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>selectAccident</title>
	
</head>
<body>
	<p>심사 할 보험을 선택해주십시오.</p>
	<form action="selectAccident">
		<table border="1">
			<thead>
				<tr>
				<th>사고번호</th>
				<th>가입자명</th>
					<th>전화번호</th>
					<th>사고날짜</th>
					<th>사고내용</th>
					<th>총비용</th>
					<th>손해정도</th>
					<th>보상종류</th>
					<th>지급여부</th>
					<th>책임비율</th>
					<th>책임비용</th>
				</tr>
			</thead>
			<tbody> 
				<c:forEach var="list" items="${AccidentList}" varStatus="status">
					<tr>
					<td><c:out value="${list.accidentID}" /></td>
					<td><c:out value="${list.customerName}" /></td>
						<td><c:out value="${list.customerPhoneNum}" /></td>
						<td><c:out value="${list.accidentDate}" /></td>
						<td><c:out value="${list.content}" /></td>
						<td><c:out value="${list.totalCost}" /></td>
						<td><c:out value="${list.damagePer}" /></td>
						<td><c:out value="${list.kindOfCost}" /></td>
						<td><c:out value="${list.payCompleted}" /></td>
						<td><c:out value="${list.liablityRate}" /></td>
						<td><c:out value="${list.liablityCost}" /></td>
				
						<td><button type="submit" name="select" value="compensation <c:out value="${status.index}"/>">보상금 지급</button></td>
						<td><button type="submit" name="select" value="modification <c:out value="${status.index}"/>">수정</button></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
	<p>${NotAccident}</p>
	</form>

</body>
</html>