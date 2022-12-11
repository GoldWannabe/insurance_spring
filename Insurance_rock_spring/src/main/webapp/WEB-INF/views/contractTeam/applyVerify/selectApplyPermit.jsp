<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>selectApplyPermit</title>
<script type="text/javascript">
	function setReason() {
		 var reason = prompt("반려 이유를 입력해 주세요.");
		 while(!reason){
			 alert("사유를 적어주세요");
			 var reason = prompt("반려 이유를 입력해 주세요.");
			};
		 
		 var reasonInput = document.getElementById("reason");
		 reasonInput.value = reason;
		 return true;
	}
</script>
</head>
<body>
	<p>원하시는 메뉴에 해당하는 버튼을 눌러주세요.</p>
	<br>
	<form action="selectApplyPermit">
		<p>가입 요청 보험료: ${ApplyContract.insuranceFee}</p>
		<p>기준 보험료: ${ApplyContract.standardFee}</p>
		<p>담보액: ${ApplyContract.securityFee}</p>
		<p>가입기간: ${ApplyContract.period}</p>
		<p>납부방식(주기): ${ApplyContract.paymentCycle}</p>
		<p>가입 조건: ${ApplyContract.applyCondition}</p>
		<p>재질: ${Rank.material}</p>
		<p>용도: ${Rank.purpose}</p>
		<p>크기(평수): ${Rank.scale}</p>
		<p>고층여부: ${Rank.height}</p>
		<p>소방시설 점수: ${Rank.fireFacilities}</p>
		<p>주변시설 점수: ${Rank.surroundingFacilities}</p>
		<p>최종등급: ${ApplyContract.totalRank}</p>
		<input type="hidden" id="reason" name="reason" value="">
		<div>
			<button type="submit" name="selectPerit" value="permit">계약</button>
			<button type="submit" name="selectPerit" value="notPermit" onclick="setReason()">반려</button>
			<button type="submit" name="selectPerit" value="cancel">취소</button>
		</div>
	</form>


</body>
</html>