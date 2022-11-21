<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>InsuranceSearchScrean</title>

</head>
<body>

	<table border="1">
		<thead>
			<tr >
				<th>보험명</th>
				<th>보험 종류</th>
			</tr>
		</thead>

		<tbody>
			<tr th:each = " list: ${tableList}">
				<td><span th:text = "${list.name}"></span></td>
				<td><span th:text = "${list.type}"></span></td>
			</tr>
		</tbody>
	</table>
	
	<form action="customerInfo">
		<p>검색하고자 하는 보험 이름을 입력해주십시오.</p>

		보험 이름: <input type="text" name="inusranceName">
		<button type="submit" name="search" value="search" />
		검색
		</button>
	</form>
</body>
</html>