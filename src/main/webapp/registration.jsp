<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Registration Form</title>

<style>
.error {
	color: #ff0000;
}
</style>

</head>

<body>

	<h2>Registration Form</h2>

    <form:form method="POST" modelAttribute="user">
    		<table>
			<tr>
				<td><label for="username">username: </label></td>
				<td><form:input type="text" path="username" id="username" /></td>
			</tr>

			<tr>
				<td><label for="password">password: </label></td>
				<td><form:input path="password" id="password" /></td>
			</tr>
			<tr>
			<td>
			<form:input type="hidden" path="enabled" id="password" value="1" />
			<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			<input type="submit" />
			</td>
			</tr>
		</table>
	</form:form>
	<br />
	<br />
</body>
</html>