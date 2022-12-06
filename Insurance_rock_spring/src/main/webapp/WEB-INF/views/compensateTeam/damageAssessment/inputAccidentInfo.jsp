<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>inputAccidentInfo</title>
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
	<form action="inputAccidentInfo">
		사고날짜: <input type="date" name="accidentDate"placeholder="ex)0000-00-00"  onkeyup="noSpaceForm(this);"  required><br>
		사고내용: <input type="text" name="content"  required><br> 
		총비용: <input type="number" name="totalCost" min="0"  onkeyup="noSpaceForm(this);" required><br>
		손해율: <input type="number" name="damagePer" min="0" max="100" onkeyup="noSpaceForm(this);"  required><br>
		보상종류: <input type="text" name="kindOfCost" min="0"   required><br>
		책임비율: <input type="number" name="liablityRate" min="0" max="100" onkeyup="noSpaceForm(this);" required><br>
		<button type="submit">사고 접수하기</button>
	</form>
</body>
</html>