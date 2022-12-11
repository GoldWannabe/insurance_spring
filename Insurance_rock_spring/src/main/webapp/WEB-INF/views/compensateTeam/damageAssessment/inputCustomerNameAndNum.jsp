<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>inputCustomerNameAndNum</title>
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
<p>고객님의 계약 중 사고를 추가할 계약을 검색합니다.</p>
	<form action="inputCustomerNameAndNum">
		가입자명: <input type="text" name="customerName"  placeholder="이름을 적어주세요" onkeyup="noSpaceForm(this);" required /><br>
		연락처: <input type="number" name="customerPhoneNum" maxlength="11" placeholder="ex)000-0000-0000" onkeyup="noSpaceForm(this);"  required  /><br>
		<button type="submit">계약 검색</button>
	</form>
</body>
</html>