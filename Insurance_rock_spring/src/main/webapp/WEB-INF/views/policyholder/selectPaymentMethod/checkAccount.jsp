<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>checkAccount</title>
</head>
	<body>
		<form action="checkAccount">
			<input type="hidden" id="method" name="method">
			 <input type="text" id="accountType" style="border: none; background: transparent;" readonly>
			 <p>카드사명/은행명: ${Account.bankName}</p>
			 <p>카드번호/계좌번호: ${Account.accountNum}</p>
			<button type="submit" name="selectCheck" value="check">납부</button>
			<button type="submit" name="selectCheck" value="edit">수정</button>
			<button type="submit" name="selectCheck" value="cancel">취소</button>
		</form>
		
		<script type="text/javascript">
			if(${Account.checkBank}){
				let accountType = document.getElementById("accountType");
				accountType.value = "은행";
				
				let method = document.getElementById("method");
				method.value = "bank";
			} else if(${Account.checkCard}){
				let accountType = document.getElementById("accountType");
				accountType.value = "카드";
				
				let method = document.getElementById("method");
				method.value = "card";
			}
		</script>
	</body>
</html>