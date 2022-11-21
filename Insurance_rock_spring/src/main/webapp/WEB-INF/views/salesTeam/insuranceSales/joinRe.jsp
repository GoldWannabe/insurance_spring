<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>InsuranceSearchScrean</title>

</head>
<body>
	<form action="rejoin">
		<p>재가입할 회원을 검색해주세요</p>

		고객 이름: <input type="text" name="customerName"> <br>
		전화번호: <input type="text" name="phoneNum"><br>
		<button type="submit" name="search" value="search" >
		검색
		</button>
	</form>

	<table border="1">
		<thead>
			<tr >
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
			<tr th:each = "list: ${tableList}">
				<td><span th:text = "${list.customerName}"></span></td>
				<td><span th:text = "${list.phoneNum}"></span></td>
				<td><span th:text = "${list.insuranceName}"></span></td>
				<td><span th:text = "${list.paymentCycle}"></span></td>
				<td><span th:text = "${list.insuranceFee}"></span></td>
				<td><span th:text = "${list.securityFee}"></span></td>
				<td><span th:text = "${list.period}"></span></td>
				<td><span th:text = "${list.faliContract,}"></span></td>
			</tr>
		</tbody>
	</table>
	
	
</body>
</html>