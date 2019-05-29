<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>暇潰し</title>
</head>
<body>
	<jsp:include page="/WEB-INF/component/header.jsp"/>
	<form action="/HIMA/mine/act" method="post" id="actForm">
		行為：
		<input type="radio" name="action" id="dig" value="1" checked/><label for="dig">掘る</label>
		<input type="radio" name="action" id="flag" value="2"/><label for="flag">フラグ立つ</label>
		<input type="hidden" name="y" id="y"/>
		<input type="hidden" name="x" id="x"/>
	</form>
	<table>
		<c:forEach var="row" items="${dispMap}">
			<tr>
				<c:forEach var="cell" items="${row}">
					<td>${cell}</td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
	<p>${message}</p>
</body>
<script>
	function act(y, x) {
		document.getElementById("y").value = y;
		document.getElementById("x").value = x;
		document.getElementById("actForm").submit();
		return false;
	}
</script>
</html>