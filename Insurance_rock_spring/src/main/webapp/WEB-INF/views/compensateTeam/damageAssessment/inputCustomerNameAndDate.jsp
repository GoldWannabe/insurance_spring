<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>inputCustomerNameAndDate</title>
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
	<form action="inputCustomerNameAndDate">
		이름: <input type="text" name="customerName" placeholder="이름을 적어주세요" onkeyup="noSpaceForm(this);" required><br>
		사고날짜: <input type="date" name="accidentDate" placeholder="ex)0000-00-00"  onkeyup="noSpaceForm(this);" required><br>
		<button type="submit">사고내역 검색하기</button>
	</form>
</body>
</html>