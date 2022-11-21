<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>SalesTeam</title>

</head>
<body>
	<p>원하시는 메뉴에 해당하는 버튼을 눌러주세요.</p>  <br>
	<div>
		<form action = "salesTeamMenu">
			<button type="submit" name = "menu" value="insuranceSales">보험판매</button>
			<button type="submit" name = "menu" value="customerManagement">고객 정보 관리</button>
			<button type="submit" name = "menu" value="channelManagement">판매채널 관리</button>
			<button type="submit" name = "menu" value="cancellation">취소</button>
		</form>
	</div>
	
</body>
</html>