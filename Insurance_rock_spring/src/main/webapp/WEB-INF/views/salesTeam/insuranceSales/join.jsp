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
				confirm("회원가입이 완료되었습니다.") 
			//location.replace("design")
			return true;
		}
	}
		
	</script>

</head>
<body>
<p>신규고객으로 회원가입을 진행합니다.</p>
<form action ="finishSales" onload="CompletionPopup(${Finish})" onsumbit = "return  confirm("회원가입이 완료되었습니다.");">
</form>
	<form action="join" >
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
		
		담보액: <input type="text" name="securityFee"><br>
		보험료: <input type="text" name="insuranceFee"><br>
		납부방식(paymentCycle): <input type="text" name="paymentCycle"><br>
		가입기간: <input type="text" name="period"><br>
		소화시설(Float) : <input type="text" name="fireFedilities"><br>
		스케일(Int): <input type="text" name="scale"><br>
		주변시설(Float): <input type="text" name="surroundingFedilities"><br>
		높이가 15층 이상입니까? <br>
		<input type="radio" name="height" value="true" />예
		<input type="radio" name="height" value="false" />아니오 <br> <br> 
		건물 재질(rock, wood etc.)<br>
		<input type="radio" name="meterial" value="1" />1.wood
		<input type="radio" name="meterial" value="2"/>2.rock 
		<input type="radio" name="meterial" value="3" />3.concrete
		<input type="radio" name="meterial" value="4" />4.iron
		<input type="radio" name="meterial" value="5" />5.brick <br> <br> 
		건물 목적(living, factory etc.)<br> 
		<input type="radio" name="goal" value="1" />1.living
		<input type="radio" name="goal" value="2"/>2.factory 
		<input type="radio" name="goal" value="3" />3.culturalAsset
		<input type="radio" name="goal" value="4" />4.store
		<input type="radio" name="goal" value="5" />5.office 
		<input type="radio" name="goal" value="6" />6.corPark
		<input type="radio" name="goal" value="7" />7.warehouse<br> <br> 
		
		<button type="submit" name = "join" value="join">가입 신청하기</button>
		<button type="submit" name = "join" value="cancel">취소</button>
	</form>
	
</body>	
</html>