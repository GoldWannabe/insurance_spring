<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<script>

	function UseStandardRate(check) {
		  if (confirm('기존 요율을 사용하시겠습니까? ${PremiumRate[0]} ${PremiumRate[1]} ${PremiumRate[2]} ')==1) {
			check.value = "confirm";
		  } else {
			check.value = "cancel";
		  }
	}
	
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
	<form action = inputInsuranceInfo>
		<p>보험 종류: ${InsuranceType}</p>
		<p>장기 여부: ${LongTerm}</p>
		<br> 보험이름: <input type="text" name="insuranceName"  onkeyup="noSpaceForm(this);" required/> <span style="color:red">${OverlapError}</span> <br>
		특약: <input type="text" name="specialContract"  onkeyup="noSpaceForm(this);" required/> <br>
		가입조건: <input type="text" name="applyCondition"  onkeyup="noSpaceForm(this);" required/> <br>
		보상 조건: <input type="text" name="compensateCondition"  onkeyup="noSpaceForm(this);" required/> <br>
		설명: <input type="text" name="explanation"  onkeyup="noSpaceForm(this);" required/> <br>
		<input name = "check" type="submit" value="다음" onclick="UseStandardRate(this);"/>
	</form>
</body>
</html>