<%@page import="com.mju.spring.entity.Contract"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>selectModefication</title>
	<script>
        function noSpaceForm(obj) {             
            var str_space = /\s/;             
            if(str_space.exec(obj.value)) {     
                alert("해당 항목에는 공백을 사용할 수 없습니다.\n\n공백이 제거됩니다.");
                obj.focus();
                obj.value = obj.value.replace(' ',''); 
                return false;
            }
        }
        
	</script>
</head>
<body>
	<p>-----사고내역 정보--------</p>
	<form action="selectModification">
		<table border="1">
			<thead>
				<tr>
					<th>사고번호</th>
					<th>가입자명</th>
					<th>연락처</th>
					<th>지급여부</th>
					<th>계약상품</th>
				</tr>
			</thead>
			<tbody> 									
					<tr>
						<td><c:out value="${AccidentID}"/> </td>
						<td><c:out value="${CustomerName}"/></td>						
						<td><c:out value="${CustomerPhoneNum}"/></td>
						<td><c:out value="${PayCompleted}"/></td>
						<td><c:out value="${ContractID}"/></td>
					</tr>
			</tbody>

		</table>
		사고날짜: <input type="date" name="accidentDate" value="${AccidentDate}" onkeyup="noSpaceForm(this);" required><br>
		사고내용: <input type="text" name="content"  value="${Content}" onkeyup="noSpaceForm(this);"  required><br>
		비용종류: <input type="text" name="kindOfCost"  value="${KindOfCost}" onkeyup="noSpaceForm(this);"  required><br>
		손해종류: <input type="text" name="damagePer" value="${DamagePer}"  onkeyup="noSpaceForm(this);"  required><br>
		총비용: <input type="number" name="totalCost"  value="${TotalCost}"  onkeyup="noSpaceForm(this);"  required><br>
		책임비용: <input type="number" name="liablityCost"   value="${LiablityCost}" onkeyup="noSpaceForm(this);"  required><br>
		책임비율: <input type="number" name="liablityRate"  value="${LiablityRate}" onkeyup="noSpaceForm(this);"  required><br>
		<button type="submit">수정</button>

	</form>

</body>
</html>