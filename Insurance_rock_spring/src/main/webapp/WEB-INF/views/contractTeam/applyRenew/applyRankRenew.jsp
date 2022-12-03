<%@page import="com.mju.spring.entity.Contract"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>applyRankRenew</title>
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
	<p>등급 정보들을 다시 입력해주세요.</p>
	<form action="applyRankRenew">
		소화시설(Float) <input type="number" name="rankInfo" placeholder="실수" onkeyup="noSpaceForm(this);" required/> <br>
		스케일(Int) <input type="number" name="rankInfo" placeholder="정수" onkeyup="noSpaceForm(this);" required/> <br>
		주변시설(Float) <input type="number" name="rankInfo" placeholder="실수" onkeyup="noSpaceForm(this);"required/> <br>
		높이가 15층 이상입니까? <br>
		<input type="radio" name="height" value="true" />예
		<input type="radio" name="height" value="false" />아니오 <br> <br> 
		건물 재질(rock, wood etc.)<br>
		<input type="radio" name="meterial" value="wood" />1.wood
		<input type="radio" name="meterial" value="rock"/>2.rock 
		<input type="radio" name="meterial" value="concrete" />3.concrete
		<input type="radio" name="meterial" value="iron" />4.iron
		<input type="radio" name="meterial" value="brick" />5.brick <br> <br> 
		건물 목적(living, factory etc.)<br> 
		<input type="radio" name="goal" value="living" />1.living
		<input type="radio" name="goal" value="factory"/>2.factory 
		<input type="radio" name="goal" value="culturalAsset" />3.culturalAsset
		<input type="radio" name="goal" value="store" />4.store
		<input type="radio" name="goal" value="office" />5.office 
		<input type="radio" name="goal" value="corPark" />6.corPark
		<input type="radio" name="goal" value="warehouse" />7.warehouse<br> <br> 
		<div>
			<button type="submit" name="applyRenew" value="applyRankRenew">등급 갱신 신청</button>
		</div>

	</form>

</body>
</html>