<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>暇潰し</title>
</head>
<body>
	<form:form action="/HIMA/sudoku/levelSelect" modelAttribute="sudokuLevelSelectForm">
		<form:select path="level" items="${levelMap}"/><br/>
		<form:button>確認</form:button>
	</form:form>
</body>
</html>