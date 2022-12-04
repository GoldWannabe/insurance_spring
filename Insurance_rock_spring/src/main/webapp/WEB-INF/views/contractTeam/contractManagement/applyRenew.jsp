<%@page import="com.mju.spring.entity.Contract"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>applyRenew</title>
	<script>
        // 공백 사용 못 하게
        function noSpaceForm(obj) {             
            var str_space = /\s/;             // 공백 체크 
            if(str_space.exec(obj.value)) {     // 공백 체크
                alert("해당 항목에는 공백을 사용할 수 없습니다.\n\n공백이 제거됩니다.");
                obj.focus();
                obj.value = obj.value.replace(' ',''); // 공백제거
                return false;
            }
        }
        
	</script>
</head>
<body>
	<form action="applyRenew">
	<p>-----------계약 갱신 등급-----------</p>
		소화시설(Float) <input type="number" name="fireFacilities" placeholder="실수" onkeyup="noSpaceForm(this);" required/> <br>
		스케일(Int) <input type="number" name="scale" placeholder="정수" onkeyup="noSpaceForm(this);" required/> <br>
		주변시설(Float) <input type="number" name="surroundingFacilities" placeholder="실수" onkeyup="noSpaceForm(this);"required/> <br>
		높이가 15층 이상입니까? <br>
		<input type="radio" name="height" value="true" required/>예
		<input type="radio" name="height" value="false" />아니오 <br> <br> 
		건물 재질(rock, wood etc.)<br>
		<input type="radio" name="material" value="wood" required/>1.wood
		<input type="radio" name="material" value="rock"/>2.rock 
		<input type="radio" name="material" value="concrete" />3.concrete
		<input type="radio" name="material" value="iron" />4.iron
		<input type="radio" name="material" value="brick" />5.brick <br> <br> 
		건물 목적(living, factory etc.)<br> 
		<input type="radio" name="purpose" value="living" required/>1.living
		<input type="radio" name="purpose" value="factory"/>2.factory 
		<input type="radio" name="purpose" value="culturalAsset" />3.culturalAsset
		<input type="radio" name="purpose" value="store" />4.store
		<input type="radio" name="purpose" value="office" />5.office 
		<input type="radio" name="purpose" value="corPark" />6.corPark
		<input type="radio" name="purpose" value="warehouse" />7.warehouse<br> <br> 
	<p>------------계약 갱신 기타---------------</p>
		담보액 : <input type="number" name="securityFee" onkeyup="noSpaceForm(this);" required/> <br>
		보험료 : <input type="number" name="insuranceFee" onkeyup="noSpaceForm(this);" required/> <br>
		납부방식 : <input type="number" name="paymentCycle" placeholder="1~12개월이내" min="1" max="12" onkeyup="noSpaceForm(this);"required/> <br>
		연장 기간 :  <input type="number" name="period" placeholder="6개월이상" min="6" onkeyup="noSpaceForm(this);"required/> <br>
		
		<div>
			<button type="submit">등급 갱신 신청</button>
		</div>

	</form>

</body>
</html>