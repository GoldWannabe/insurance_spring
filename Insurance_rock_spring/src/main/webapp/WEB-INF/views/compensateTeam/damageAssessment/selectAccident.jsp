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
				<th>가입자명</th>
					<th>전화번호</th>
					<th>사고날짜</th>
					<th>사고내용</th>
					<th>총비용</th>
					<th>손해율</th>
					<th>보상종류</th>
					<th>지급여부</th>
					<th>책임비율</th>
					<th>보상금액</th>
					<th>비고</th>
				</tr>
			</thead>
			<tbody> 
				<c:forEach var="list" items="${Accidend}" varStatus="status">
					<tr>
					<td><c:out value="${Accidend.customerName}" /></td>
						<td><c:out value="${Accidend.customerPhoneNum}" /></td>
						<td><c:out value="${Accidend.accidentDate}" /></td>
						<td><c:out value="${Accidend.content}" /></td>
						<td><c:out value="${Accidend.totalCost}" /></td>
						<td><c:out value="${Accidend.damagePer}" /></td>
						<td><c:out value="${Accidend.kindOfCost}" /></td>
						<td><c:out value="${Accidend.payCompleted}" /></td>
						<td><c:out value="${Accidend.liablityRate}" /></td>
						<td><c:out value="${Accidend.liablityCost}" /></td>
						<td><input type="hidden" name="accidentId" value="<c:out value="${list.accidentId}"/>"></td>
						<td><button type="submit" name=select value="compensate">보상금 지급</button></td>
						<td><button type="submit" name=select value="edit">수정</button></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>

	</form>

</body>
</html>