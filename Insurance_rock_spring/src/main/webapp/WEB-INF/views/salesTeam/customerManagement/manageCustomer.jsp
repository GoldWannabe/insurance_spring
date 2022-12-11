<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>manageCustomer</title>

</head>
<body>
<p>-------고객정보-----</p>
	<form action="manageCustomer">
		가입자명 :	 ${CustomerName} <br><br>
		주민/사업자번호:	 ${SSN} <br><br>
		연락처 :  ${PhoneNum} <br><br>
		주소:  ${Address}<br><br>
		성별 :  ${Sex} <br><br>
		가입보험 개수 :  ${InsuranceNum}<br><br>
		은행명:  ${BankName}<br><br>
		계좌번호 :  ${AccountNum}<br>
		<button type="submit" name = "manage" value="update">수정</button>
		<button type="submit" name = "manage" value="add">추가</button>
		<button type="submit" name = "manage" value="delete">삭제</button>
	</form>
	
</body>	
</html>