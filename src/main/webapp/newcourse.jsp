<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form:form method="POST" modelAttribute="course">
		<table>
			<tr>
				<td><label for="name">name: </label></td>
				<td><form:input type="text" path="name" id="name" /></td>
			</tr>

			<tr>
				<td><label for="date">date: </label></td>
				<td><form:input path="date" id="date" /></td>
			</tr>
			<tr>
				<td><label for="link">link: </label></td>
				<td><form:input path="link" id="link" /></td>
			</tr>
			<tr>
				<td><input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /> <input type="submit" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>