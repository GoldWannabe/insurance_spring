<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>JoinCustomer</title>

<script>
	function CompletionPopup(boolean finishSales) {
		if (finishSales) {
				confirm("ȸ�������� �Ϸ�Ǿ����ϴ�.") 
			//location.replace("design")
			return true;
		}
	}
		
	</script>

</head>
<body>
<p>�ű԰����� ȸ�������� �����մϴ�.</p>
<form action ="finishSales" onload="CompletionPopup(${Finish})" onsumbit = "return  confirm("ȸ�������� �Ϸ�Ǿ����ϴ�.");">
</form>
	<form action="join" >
		�� �̸�: <input type="text" name="customerName"> <br>
		�ֹ�/����� ��ȣ: <input type="text" name="SSN"><br>
		��ȭ��ȣ: <input type="text" name="phoneNum"><br>
		�ּ�: <input type="text" name="address"><br>
		����: [male, female,none]<br>
		<input type="radio" name="gender" value="male" />male
		<input type="radio" name="gender" value="female" />female
		<input type="radio" name="gender" value="none" />none <br><br>
		���� �̸�: <input type="text" name="bankName"><br>
		���¹�ȣ: <input type="text" name="accountNum"><br>
		
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
		<input type="radio" name="meterial" value="wood" />1.wood
		<input type="radio" name="meterial" value="rock"/>2.rock 
		<input type="radio" name="meterial" value="concrete" />3.concrete
		<input type="radio" name="meterial" value="iron" />4.iron
		<input type="radio" name="meterial" value="brick" />5.brick <br> <br> 
		�ǹ� ����(living, factory etc.)<br> 
		<input type="radio" name="purpose" value="living" />1.living
		<input type="radio" name="purpose" value="factory"/>2.factory 
		<input type="radio" name="purpose" value="culturalAsset" />3.culturalAsset
		<input type="radio" name="purpose" value="store" />4.store
		<input type="radio" name="purpose" value="office" />5.office 
		<input type="radio" name="purpose" value="corPark" />6.corPark
		<input type="radio" name="purpose" value="warehouse" />7.warehouse<br> <br> 
		
		<button type="submit" name = "join" value="join">���� ��û�ϱ�</button>
		<button type="submit" name = "join" value="cancel">���</button>
	</form>
	
</body>	
</html>