<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>inputAccount</title>
</head>
	<body>
		<form action="inputAccount">
			납부방법 <br> <input type="radio" id="card" name="method" value="card" checked="${Account.checkCard}" required/>카드
			<input type="radio" id="bank" name="method" value="bank" checked="${Account.checkBank}"/>통장 <br> 
			카드사명/은행명: <input type="text" name="AccountName" value="${Account.bankName}" required><br>
			카드번호/계좌번호: <input type="text" name="AccountNum" value="${Account.accountNum}" required><br>
			<button type="submit" name="selectCheck" value="check">확인</button>
			<button type="submit" name="selectCheck" value="cancel">취소</button>
		</form>
		
		<script type="text/javascript">
			if(${Account.checkBank}){
				document.getElementById("bank").checked = true;
			} else if(${Account.checkCard}){
				let method = document.getElementById("card").checked = true;
			}
		</script>
	</body>
</html>