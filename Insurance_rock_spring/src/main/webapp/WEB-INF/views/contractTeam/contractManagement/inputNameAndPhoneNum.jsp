<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>inputNameAndPhoneNum</title>
	<script>
        // 공백 사용 못 하게
        function noSpaceForm(obj) {             
            var str_space = /\s/;             // 공백 체크 
            if(str_space.exec(obj.value)) {     // 공백 체크
                alert("해당 항목에는 공백을 사용할 수 없습니다.\n\n공백이 제거됩니다.");
                obj.focus();
                obj.value = obj.value.replace(' ',''); // 공백제거
                return false;
            }
        }
        
	</script>
</head>
<body>
	<form action = inputNameAndPhoneNum>
	<p>검색할 계약의 가입자명과 연락처를 입력해주세요</p>

		가입자명: <input type="text" name="customerName" placeholder="이름을 적어주세요" required/> <br>
		연락처: <input type="number" name="customerPhoneNum" placeholder="ex)000 0000 0000" maxlength="11"  onkeyup="noSpaceForm(this);"required/> <br>
		<button type="submit"  name = "next" value="next">다음</button>
	</form>
</body>
</html>