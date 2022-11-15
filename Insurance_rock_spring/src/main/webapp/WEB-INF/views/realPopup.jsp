<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>popup</title>

<script type="text/javascript">
	//1.빈창 팝업 생성
	var url = "popupList";
	var windowTargetName = "targetName";
	var features = "scrollbars=yes,width=700,height=500,location=no, resizable=yes";
	//call
	window.open(url, windowTargetName, features);

//	if(popList의 확인 버튼이 눌렸으면~){
		//여기
		//location.replace("design");
	//}else{
		//여기
	//}
	
	
	
	// 2.POST로 데이터 전달
	myForm.action = "popupList"; // 이동
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