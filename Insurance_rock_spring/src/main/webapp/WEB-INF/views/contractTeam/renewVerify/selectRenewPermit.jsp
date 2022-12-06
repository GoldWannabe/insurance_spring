<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>selectRenewPermit</title>
<script type="text/javascript">
	function setReason() {
		 var reason = prompt("반려 이유를 입력해 주세요.");
		 var reasonInput = document.getElementById("reason");
		 reasonInput.value = reason;
		 return true;
	}
</script>
</head>
<body>
	<p>원하시는 메뉴에 해당하는 버튼을 눌러주세요.</p>
	<br>

	<form action="selectRenewPermit">

		<p>기존 보험료: ${RenewContract.previousInsuranceFee} --> 갱신 후 보험료: ${RenewContract.newInsuranceFee}</p>
		<p>기존 담보액: ${RenewContract.previousSecurityFee} --> 갱신 후 담보액: ${RenewContract.newSecurityFee}</p>
		<p>현재 만료일: ${RenewContract.endDate}</p>
		<p>연장기간: ${RenewContract.period}</p>
		<p>재질: ${PreviousRank.material} --> ${newRank.material}</p>
		<p>용도: ${PreviousRank.purpose} --> ${newRank.purpose}</p>
		<p>크기(평수): ${PreviousRank.scale} --> ${newRank.scale}</p>
		<p>고층여부: ${PreviousRank.height} --> ${newRank.height}</p>
		<p>소방시설 점수: ${PreviousRank.fireFacilities} --> ${newRank.fireFacilities}</p>
		<p>주변시설 점수: ${PreviousRank.surroundingFacilities} --> ${newRank.surroundingFacilities}</p>
		<p>최종등급: ${RenewContract.totalRank}</p>
		<input type="hidden" id="reason" name="reason" value="">
		<div>
			<button type="submit" name="selectPerit" value="permit">계약</button>
			<button type="submit" name="selectPerit" value="notPermit" onclick="setReason()">반려</button>
			<button type="submit" name="selectPerit" value="cancel">취소</button>
		</div>
	</form>


</body>
</html>