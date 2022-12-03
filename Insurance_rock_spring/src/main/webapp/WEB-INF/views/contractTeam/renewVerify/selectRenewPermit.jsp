<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>selectRenewPermit</title>

</head>
<body>
	<p>원하시는 메뉴에 해당하는 버튼을 눌러주세요.</p>
	<br>

	<form action="selectRenewPermit">

		<p>이전의 보험료: ${Insurance.insuranceName}</p>
		<p>신규 보험료: ${Insurance.insuranceType}</p>
		<p>이전 등급: ${Insurance.standardFee}</p>
		<p>현재 등급,: ${Insurance.specialContract}</p>
		<p>기존 만기일: ${Insurance.longTerm}</p>
		<p>추가 가입 기간: ${Insurance.applyCondition}</p>
		
		<div>
			<button type="submit" name="selectPerit" value="permit">계약</button>
			<button type="submit" name="selectPerit" value="notPermit">반려</button>
			<button type="submit" name="selectPerit" value="cancel">취소</button>
		</div>
	</form>


</body>
</html>