<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>popup</title>

<script type="text/javascript">
	//1.��â �˾� ����
	var url = "popupList";
	var windowTargetName = "targetName";
	var features = "scrollbars=yes,width=700,height=500,location=no, resizable=yes";
	//call
	window.open(url, windowTargetName, features);

//	if(popList�� Ȯ�� ��ư�� ��������~){
		//����
		//location.replace("design");
	//}else{
		//����
	//}
	
	
	
	// 2.POST�� ������ ����
	myForm.action = "popupList"; // �̵�
	myForm.method = "post";
	myForm.target = windowTargetName;
	myForm.submit();
</script>
<style>
</style>
</head>
<body>
	<form id="myForm" name="myForm">
		<input type="hidden" name="sample" id="sample" value="sample" />
	</form>
</body>
</html>