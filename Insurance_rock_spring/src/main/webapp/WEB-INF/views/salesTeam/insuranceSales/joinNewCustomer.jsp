<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>JoinNewCustomer</title>

	<script>
	function CompletionPopup() {
		if (confirm("회원가입이 완료되었습니다.")) {
			//location.replace("design")
			return true;
		} else
			return false;
	</script>

</head>
<body>
<p>신규고객으로 회원가입을 진행합니다.</p>
	<form action="continuously" >
		고객 이름: <input type="text" name="customerName"> <br>
		주민/사업자 번호: <input type="text" name="registrationNum"><br>
		전화번호: <input type="text" name="phoneNum"><br>
		주소: <input type="text" name="address"><br>
		성별: [male, female,none]<br>
		<input type="radio" name="gender" value="male" />male
		<input type="radio" name="gender" value="female" />female
		<input type="radio" name="gender" value="none" />none <br><br>
		은행 이름: <input type="text" name="bankName"><br>
		계좌번호: <input type="text" name="accountNum"><br>
		
		<button  id="myBtn" type="submit" name = "join" value="joinRequest">회원가입하기</button>
		<button type="submit" name = "join" value="cancellation">취소</button>
	</form>
	
</body>	
</html>