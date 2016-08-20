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
<spring:url value="/resources/template/css/flat-ui.min.css"
	var="flatuiCss" />
<spring:url value="/resources/template/css/app.css" var="appCss" />
<spring:url value="/resources/template/js/bootstrap.min.js"
	var="bootstrapJs" />
<spring:url value="/resources/template/js/flat-ui.min.js" var="flatuiJs" />

<spring:url value="/resources/template/js/interface.js"
	var="interfaceJs" />
<spring:url value="/resources/template/js/app.js" var="appJs" />

<spring:url value="/resources/template/img/favicon.ico" var="favicon" />
<link rel="icon" href="${favicon}" />

<link href="${bootstrapCss}" rel="stylesheet">
<link href="${flatuiCss}" rel="stylesheet">
<link href="${appCss}" rel="stylesheet">
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div class="container">
		<div class="menu">
			<div class="navbar navbar-default" role="navigation">
				<div class="container-fluid">
					<div>
						<ul class="nav navbar-nav">
							<li class="active"><a href="#"><img
									src="${pageContext.servletContext.contextPath}/resources/template/img/icons/Courses.png "
									alt="Consulter les cours" title="Consulter les cours"
									data-toggle="tooltip" data-placement="bottom" /></a></li>
							<li class="active"><a href="#"><img
									src="${pageContext.servletContext.contextPath}/resources/template/img/icons/Exercices.png "
									alt="Consulter les exercices" title="Consulter les exercices"
									data-toggle="tooltip" data-placement="bottom" /></a></li>
							<li class="active"><a href="#"><img
									src="${pageContext.servletContext.contextPath}/resources/template/img/icons/Task.png "
									alt="Exercices à faire" title="Exercices à faire"
									data-toggle="tooltip" data-placement="bottom" /></a></li>
							<li class="active"><a href="#"><img
									src="${pageContext.servletContext.contextPath}/resources/template/img/icons/Timing.png "
									alt="Emplois du temps" title="Emplois du temps"
									data-toggle="tooltip" data-placement="bottom" /></a></li>
							<li class="active"><a href="#"><img
									src="${pageContext.servletContext.contextPath}/resources/template/img/icons/Courses.png "
									alt="Messagerie" title="Messagerie" data-toggle="tooltip"
									data-placement="bottom" /></a></li>
							<li class="active"><a href="#"><img
									src="${pageContext.servletContext.contextPath}/resources/template/img/icons/Logout.png "
									alt="Logout" title="Logout" data-toggle="tooltip"
									data-placement="bottom" id="LogoutButton"/></a></li>

						</ul>
					</div>
				</div>
			</div>
		</div>
		
		<c:url value="/logout" var="logoutUrl" />
			<form action="${logoutUrl}" method="post" id="logoutForm">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
			
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${bootstrapJs}"></script>
	<script src="${flatuiJs}"></script>
	<script src="${appJs}"></script>
</body>
</html>