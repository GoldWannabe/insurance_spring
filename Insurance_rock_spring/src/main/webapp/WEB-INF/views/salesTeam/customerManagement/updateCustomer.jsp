<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>updateCustomer</title>

</head>
<body>
	<form action="updateCustomer">
		가입자명 : <input type="text" name="customerName" value = "${CustomerName}"onkeyup="noSpaceForm(this);" required> <br>
		주민/사업자번호 : <input type="number" name="ssn" value = "${SSN}"onkeyup="noSpaceForm(this);" required> <br>
		연락처 : <input type="number" name="phoneNum" value = "${PhoneNum}"onkeyup="noSpaceForm(this);" required> <br>
		주소 : <input type="text" name="address" value = "${Address}"onkeyup="noSpaceForm(this);" required> <br>
		성별 : [male, female,none]<br>
		<input type="radio" name="gender" value="male" checked required/>male
		<input type="radio" name="gender" value="female" />female
		<input type="radio" name="gender" value="none" />none <br><br>
		가입 보험 개수 : <input type="text" name="insuranceNum" value = "${InsuranceNum}"onkeyup="noSpaceForm(this);" required> <br>
		은행명: <input type="text" name="bankName" value = "${BankName}"onkeyup="noSpaceForm(this);" required> <br>
		계좌번호: <input type="number" name="accountNum" value = "${AccountNum}"onkeyup="noSpaceForm(this);" required> <br>
		<button type="submit" name = "manage" value="update">수정</button>
	</form>
	
</body>	
</html>