<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>addCustomer</title>
	<script>
        function noSpaceForm(obj) {             
            var str_space = /\s/;            
            if(str_space.exec(obj.value)) {     
                alert("해당 항목에는 공백을 사용할 수 없습니다.\n\n공백이 제거됩니다.");
                obj.focus();
                obj.value = obj.value.replace(' ',''); 
                return false;
            }
        }
        
	</script>
</head>
<body>
	<form action="addCustomer">
		<p>----보험 가입자 기본 정보----</p>
		가입자명 : <input type="text" name="customerName" onkeyup="noSpaceForm(this);" required> <br>
		주민/사업자번호 : <input type="number" name="ssn" onkeyup="noSpaceForm(this);" required> <br>
		연락처 : <input type="number" name="phoneNum" onkeyup="noSpaceForm(this);" required> <br>
		주소 : <input type="text" name="address" onkeyup="noSpaceForm(this);" required> <br>
		성별 : [male, female,none]<br>
		<input type="radio" name="gender" value="male" checked required/>male
		<input type="radio" name="gender" value="female" />female
		<input type="radio" name="gender" value="none" />none <br><br>
		가입 보험 개수 : <input type="text" name="insuranceNum"onkeyup="noSpaceForm(this);" required> <br>
		은행명: <input type="text" name="bankName" onkeyup="noSpaceForm(this);" required> <br>
		계좌번호: <input type="number" name="accountNum"onkeyup="noSpaceForm(this);" required> <br>
		<button type="submit" name = "manage" value="update">수정</button>
	</form>
	<script type="text/javascript">
			if(${Popup}){
				alert('${Message}');
			}
	</script>
</body>
</html>