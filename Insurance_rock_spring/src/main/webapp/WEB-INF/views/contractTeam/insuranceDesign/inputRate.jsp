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
                alert("�ش� �׸񿡴� ������ ����� �� �����ϴ�.\n\n������ ���ŵ˴ϴ�.");
                obj.focus();
                obj.value = obj.value.replace(' ',''); 
                return false;
            }
        }
        
	</script>
</head>
<body>
	<form action="inputRate">
		����1: <input type="text" name="rate1"  onkeyup="noSpaceForm(this);" required><br>
		����2: <input type="text" name="rate2"  onkeyup="noSpaceForm(this);" required><br>
		����3: <input type="text" name="rate3"  onkeyup="noSpaceForm(this);" required><br>
		<button type="submit" name = "join" value="join">���� ��û</button>
	</form>
</body>
</html>