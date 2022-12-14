<%@page import="com.mju.spring.dto.salesTeam.InsuranceSales.InsuranceDto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>InsuranceSearchScrean</title>
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


	<table border="1">
		<thead>
			<tr>
				<th>보험명</th>
				<th>보험 종류</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="list" items="${InsuranceList}" varStatus="status">
				<tr>
					<td><c:out value="${list.insuranceName}" /></td>
					<td><c:out value="${list.insuranceType}" /></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>

	<form action="inputInsuranceName">
		<p>검색하고자 하는 보험 이름을 입력해주십시오.</p>

		보험 이름: <input type="text" name="insuranceName" onkeyup="noSpaceForm(this);" required >
		<button type="submit" name="search" value="search">검색</button>
	</form>
	
	<script type="text/javascript">
			if('${Popup}'){
				alert('${Message}');
			}
		</script>
</body>
</html>