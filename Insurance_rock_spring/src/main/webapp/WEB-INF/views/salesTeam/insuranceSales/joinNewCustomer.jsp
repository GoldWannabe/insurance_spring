<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>JoinNewCustomer</title>

	<script>
	function CompletionPopup() {
		if (confirm("ȸ�������� �Ϸ�Ǿ����ϴ�.")) {
			//location.replace("design")
			return true;
		} else
			return false;
	</script>

</head>
<body>
<p>�ű԰����� ȸ�������� �����մϴ�.</p>
	<form action="continuously" >
		�� �̸�: <input type="text" name="customerName"> <br>
		�ֹ�/����� ��ȣ: <input type="text" name="registrationNum"><br>
		��ȭ��ȣ: <input type="text" name="phoneNum"><br>
		�ּ�: <input type="text" name="address"><br>
		����: [male, female,none]<br>
		<input type="radio" name="gender" value="male" />male
		<input type="radio" name="gender" value="female" />female
		<input type="radio" name="gender" value="none" />none <br><br>
		���� �̸�: <input type="text" name="bankName"><br>
		���¹�ȣ: <input type="text" name="accountNum"><br>
		
		<button  id="myBtn" type="submit" name = "join" value="joinRequest">ȸ�������ϱ�</button>
		<button type="submit" name = "join" value="cancellation">���</button>
	</form>
	
</body>	
</html>