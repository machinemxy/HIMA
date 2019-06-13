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
	<form action="/HIMA/sudoku/finish" method="post">
		<table border="1">
			<c:forEach var="i" begin="0" end="2">
				<tr>
					<c:forEach var="j" begin="0" end="2">
						<td>
							<c:forEach var="k" begin="0" end="2">
								<table border="1">
									<tr>
										<c:forEach var="l" begin="0" end="2">
											<td>
												${stage[i*3+k][j*3+l]}
											</td>
										</c:forEach>
									</tr>
								</table>
							</c:forEach>
						</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
		<button type="submit">確定</button>
		<input type="hidden" name="level" value="${level}"/>
	</form>
	<br/>
	${message}
</body>
</html>