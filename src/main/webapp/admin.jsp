<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>E-learning</title>
<spring:url value="/resources/template/css/bootstrap.min.css"
	var="bootstrapCss" />
<spring:url value="/resources/template/css/jasny-bootstrap.min.css"
	var="jasnyCss" />
<spring:url value="/resources/template/css/bootstrap-dialog.min.css"
	var="DialogCss" />
<spring:url value="/resources/template/css/app.css" var="appCss" />

<spring:url value="/resources/template/js/flat-ui.min.js" var="flatuiJs" />

<spring:url value="/resources/template/js/jasny-bootstrap.min.js"
	var="jasnyJs" />
<spring:url value="/resources/template/js/bootstrap-dialog.min.js"
	var="DialogJs" />
<spring:url value="/resources/template/js/interface.js"
	var="interfaceJs" />
<spring:url value="/resources/template/js/app.js" var="appJs" />

<spring:url value="/resources/template/img/favicon.ico" var="favicon" />
<link rel="icon" href="${favicon}" />

<link href="${bootstrapCss}" rel="stylesheet">
<link href="${jasnyCss}" rel="stylesheet">
<link href="${DialogCss}" rel="stylesheet">
<link href="${appCss}" rel="stylesheet">
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	Liste des class :
	<br />
	<ul>
		<c:forEach items="${listeClasses}" var="classe">
			<li><a
				href="${pageContext.servletContext.contextPath}/admin/classe/${classe.classeId}">${classe.name}</a></li>
		</c:forEach>
	</ul>


	<form:form method="POST" modelAttribute="classe"
		action="${pageContext.servletContext.contextPath}/admin/addClass">
		<form:input path="name" />
		<input type="submit" value="Ajouter !" />
	</form:form>


	<img
		src="${pageContext.servletContext.contextPath}/resources/template/img/icons/Logout.png "
		alt="Logout" title="Logout" data-toggle="tooltip"
		data-placement="bottom" id="LogoutButton" />
	<c:url value="/logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${flatuiJs}"></script>
	<script src="${jasnyJs}"></script>
	<script src="${DialogJs}"></script>
	<script src="${appJs}"></script>
</body>
</html>