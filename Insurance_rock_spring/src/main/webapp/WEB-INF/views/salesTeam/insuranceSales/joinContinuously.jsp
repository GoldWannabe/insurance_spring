<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>insuranceInfoOfCustomer</title>

	<script>
	function CompletionPopup() {
		alert("���� ���Խ�û�� �Ϸ� �Ǿ����ϴ�.")
	</script>

</head>
<body>
<p>�̾ ȸ������ ���� ������ �����Ͽ��ּ���.</p>
	<form action="insuranceJoinFinish"> <br>
		�㺸��: <input type="text" name="securityFee"><br>
		�����: <input type="text" name="insuranceFee"><br>
		���ι��(paymentCycle): <input type="text" name="paymentCycle"><br>
		���ԱⰣ: <input type="text" name="period"><br>
		��ȭ�ü�(Float) : <input type="text" name="fireFedilities"><br>
		������(Int): <input type="text" name="scale"><br>
		�ֺ��ü�(Float): <input type="text" name="surroundingFedilities"><br>
		���̰� 15�� �̻��Դϱ�? <br>
		<input type="radio" name="height" value="true" />��
		<input type="radio" name="height" value="false" />�ƴϿ� <br> <br> 
		�ǹ� ����(rock, wood etc.)<br>
		<input type="radio" name="meterial" value="1" />1.wood
		<input type="radio" name="meterial" value="2"/>2.rock 
		<input type="radio" name="meterial" value="3" />3.concrete
		<input type="radio" name="meterial" value="4" />4.iron
		<input type="radio" name="meterial" value="5" />5.brick <br> <br> 
		�ǹ� ����(living, factory etc.)<br> 
		<input type="radio" name="goal" value="1" />1.living
		<input type="radio" name="goal" value="2"/>2.factory 
		<input type="radio" name="goal" value="3" />3.culturalAsset
		<input type="radio" name="goal" value="4" />4.store
		<input type="radio" name="goal" value="5" />5.office 
		<input type="radio" name="goal" value="6" />6.corPark
		<input type="radio" name="goal" value="7" />7.warehouse<br> <br> 
		<button type="submit" name = "join" value="joinRequest" onClick="CompletionPopup();">���� ��û�ϱ�</button>
	</form>
	
</body>	
</html>