<%@page import="com.mju.spring.entity.Contract"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>selectRenewAndRenewCancel</title>

</head>
<body>
	<p>-----보험 세부 정보 (계약, 등급)-----</p>
	<form action="selectRenewAndRenewCancel">
		<table border="1">
			<thead>
				<tr>
					<th>계약ID</th>
					<th>보험이름</th>
					<th>가입자명</th>
					<th>연락처</th>
					<th>보험 가입일</th>
					<th>보험 만기일</th>
					<th>담보액</th>
					<th>보험료</th>
					<th>납부방식</th>
					<th>미납액</th>
					<th>건물재질<br>(등급)</th>
					<th>소화시설<br>(등급)</th>
					<th>건물높이<br>(등급)</th>
					<th>규모<br>(등급)</th>
					<th>주변시설<br>(등급)</th>
					<th>건물 목적<br>(등급)</th>
				</tr>
			</thead>
			<tbody>
					<tr>
						<td>${ContractID}</td>
						<td>${InsuranceName}</td>
						<td>${CustomerName}</td>
						<td>${CustomerPhoneNum}</td>
						<td>${StartDate}</td>
						<td>${EndDate}</td>
						<td>${SecurityFee}</td>
						<td>${InsuranceFee}</td>
						<td>${PaymentCycle}</td>
						<td>${UnpaidFee}</td>
						<td>${Material}</td>
						<td>${FireFacilities}</td>
						<td>${Height}</td>
						<td>${Scale}</td>
						<td>${SurroundingFacilities}</td>
						<td>${Purpose}</td>
					</tr>
			</tbody>
		</table>
		<p>-----보험 세부 정보(사고이력)-----</p>
		<table border="1">
			<thead>
				<tr>
					<th>계약ID</th>
					<th>사고ID</th>
					<th>사고날짜</th>
					<th>사고내용</th>
					<th>총 비용</th>
					<th>손해정도</th>
					<th>종류</th>
					<th>지급여부</th>
					<th>책이비율</th>
					<th>책임비용</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="list" items="${AccidentList}" varStatus="status">
					<tr>
						<td><c:out value="${list.contractID}" /></td>
						<td><c:out value="${list.accidentID}" /></td>
						<td><c:out value="${list.accidentDate}" /></td>
						<td><c:out value="${list.content}" /></td>
						<td><c:out value="${list.totalCost}" /></td>
						<td><c:out value="${list.damagePer}" /></td>
						<td><c:out value="${list.kindOfCost}" /></td>
						<td><c:out value="${list.payCompleted}" /></td>
						<td><c:out value="${list.liablityRate}" /></td>
						<td><c:out value="${list.liablityCost}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<button type="submit" name="RenewMenu" value="renew">계약 갱신</button>
			<button type="submit" name="RenewMenu" value="renewCancel">계약
				해지</button>
			<button type="submit" name="RenewMenu" value="cancel">취소</button>
		</div>
	</form>

</body>
</html>