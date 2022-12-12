<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>inputCustomerBasicInfo</title>
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
	<form action="inputCustomerBasicInfo">
		<p>----보험 가입자 기본 정보----</p>
		이름: <input type="text" name="customerName" onkeyup="noSpaceForm(this);" required > <br>
		연락처 : <input type="number" name="phoneNum" placeholder="ex) 000 0000 0000" maxlength="11" onkeyup="noSpaceForm(this);" required > <br>
		<button type="submit" name="search" value="search">조회</button>
	</form>
</body>
</html>