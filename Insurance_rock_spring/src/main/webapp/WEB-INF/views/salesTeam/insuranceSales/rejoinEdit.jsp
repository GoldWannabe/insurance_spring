<%@page import="com.mju.spring.entity.Contract"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>rejoinEdit</title>
<script>
function noSpaceForm(obj) {      
    var str_space = /\s/;             
    if(str_space.exec(obj.value)) {    
        alert("해당 항목에는 공백을 사용할 수 없습니다.\n\n공백이 제거됩니다.");
        obj.focus();
        obj.value = obj.value.replace(' ',''); 
    }
}
</script>
</head>
<body>
	<p>재가입을 위해 변경된 사항을 입력하여 주십시오.</p>
	<p>---변경 이전 내역---</p>
	가입자명: ${customerName}
	<br> 전화번호: ${customerPhoneNum}
	<br> 보험이름: ${insuranceName}
	<br> 납부주기: ${paymentCycle}
	<br> 보험료: ${insuranceFee}
	<br> 담보액: ${securityFee}
	<br> 가입기간: ${period}
	<br> 반려 이유: ${reason}
	<br> -----
	<form action="rejoinEdit">
		가입자명: <input type="text" name="customerName"  onkeyup="noSpaceForm(this);" required/> <br>
		전화번호: <input type="text" name="customerPhoneNum"  onkeyup="noSpaceForm(this);" required/> <br>
		납부주기: <input type="text" name="paymentCycle"  onkeyup="noSpaceForm(this);" required/> <br>
		보험료: <input type="text" name="insuranceFee"  onkeyup="noSpaceForm(this);" required/> <br>
		담보액: <input type="text" name="securityFee"  onkeyup="noSpaceForm(this);" required/> <br>
		가입기간: <input type="text" name="period"  onkeyup="noSpaceForm(this);" required/> <br>
		
		<button type="submit" name = "rejoin" value="rejoin">재가입 신청</button>
		<button type="submit" name = "rejoin" value="cancel">취소</button>
	</form>

</body>
</html>