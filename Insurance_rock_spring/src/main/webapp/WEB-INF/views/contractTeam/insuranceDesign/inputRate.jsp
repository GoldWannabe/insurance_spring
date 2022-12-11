<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
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
	<form action="inputRate">
		요율1: <input type="text" name="rate1"  onkeyup="noSpaceForm(this);" required><br>
		요율2: <input type="text" name="rate2"  onkeyup="noSpaceForm(this);" required><br>
		요율3: <input type="text" name="rate3"  onkeyup="noSpaceForm(this);" required><br>
		<button type="submit" name = "join" value="join">가입 신청</button>
	</form>
</body>
</html>