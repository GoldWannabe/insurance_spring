<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script>
function rtn(){
	if(confirm("�ٽþ��⸦ �Ͻðڽ��ϱ�?")){
		f.reset();                 //������ �ʱ�ȭ
		f.player_name.select();    //�̸� ���
		
	} else return;
}
</script>
</head>
<body oncontextmenu="return false" onselectstart="return false"
	ondragstart="return false">
	
	<form action="design" >
		�̸�: <input type="text">	
		<button onclick="rtn();">��</button>
		<button onclick="rtn();">�ƴϿ�</button>
	</form>
	
	<p>���Ⱑ �˾� â�Դϴ�.</p>
</body>
</html>