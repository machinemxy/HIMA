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
	<p>●あなた：${sessionScope.data.playerScore}</p>
	<p>▲ＣＰＵ：${sessionScope.data.cpuScore}</p>
	<table border="1">
		<c:forEach var="i" begin="0" end="2">
			<tr>
				<c:forEach var="j" begin="0" end="2">
					<td>${dispMap[i*3+j]}</td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
	<p>${message}</p>
	<p>${link}</p>
</body>
</html>