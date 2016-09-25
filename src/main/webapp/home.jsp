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
<spring:url value="/resources/template/js/bootstrap.min.js"
	var="bootstrapJs" />

<spring:url value="/resources/template/css/jasny-bootstrap.min.css"
	var="jasnyCss" />
<spring:url value="/resources/template/css/bootstrap-dialog.min.css"
	var="DialogCss" />







<spring:url value="/resources/template/js/jasny-bootstrap.min.js"
	var="jasnyJs" />
<spring:url value="/resources/template/js/bootstrap-dialog.min.js"
	var="DialogJs" />
<spring:url value="/resources/template/js/interface.js"
	var="interfaceJs" />
<%-- DATATABLE JAVASCRIPT INCLUDES  --%>

<spring:url value="/resources/template/js/jquery.dataTables.min.js"
	var="dataTablesJs" />
<spring:url value="/resources/template/js/dataTables.bootstrap.min.js"
	var="dataTablesBootstrapJs" />
<spring:url value="/resources/template/js/dataTables.buttons.min.js"
	var="dataTablesButtonsJs" />
<spring:url value="/resources/template/js/buttons.bootstrap.min.js"
	var="dataTablesBootstrapButtonsJs" />

<%-- END DATATABLE JAVASCRIPT INCLUDES --%>

<%-- DATATABLE CSS INCLUDES  --%>

<spring:url value="/resources/template/css/dataTables.bootstrap.min.css"
	var="dataTablesBootstrapCss" />
<spring:url value="/resources/template/css/buttons.bootstrap.min.css"
	var="dataTablesButtonsCss" />

<%-- END DATATABLE CSS INCLUDES  --%>

<%-- AUTOCOMPLETE JAVASCRIPT INCLUDES  --%>

<spring:url value="/resources/template/js/select2.min.js"
	var="select2Js" />

<%-- END AUTOCOMPLETE JAVASCRIPT INCLUDES --%>

<%-- AUTOCOMPLETE CSS INCLUDES  --%>

<spring:url value="/resources/template/css/select2.min.css"
	var="select2Css" />

<spring:url value="/resources/template/css/select2-bootstrap.min.css"
	var="select2BootstrapCss" />
<%-- END AUTOCOMPLETE CSS INCLUDES  --%>


<%-- APP CSS/JAVASCRIPT INCLUDES  --%>

<spring:url value="/resources/template/js/app.js" var="appJs" />
<spring:url value="/resources/template/css/app.css" var="appCss" />

<%-- END APP CSS/JAVASCRIPT INCLUDES  --%>

<spring:url value="/resources/template/img/favicon.ico" var="favicon" />
<link rel="icon" href="${favicon}" />

<link href="${bootstrapCss}" rel="stylesheet">
<link href="${jasnyCss}" rel="stylesheet">
<link href="${DialogCss}" rel="stylesheet">
<link href="${dataTablesBootstrapCss}" rel="stylesheet">
<link href="${dataTablesButtonsCss}" rel="stylesheet">
<link href="${select2Css}" rel="stylesheet">
<link href="${select2BootstrapCss}" rel="stylesheet">
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
				<div>
					<ul class="nav navbar-nav">
						<sec:authorize access="hasRole('ROLE_TEACHER')">
							<li><a href="#" data-toggle="modal" data-target="#myModal"
								id="AddCoursesButton" class="item"> <span
									class="notify-badge green glyphicon glyphicon-plus"></span><img
									src="${pageContext.servletContext.contextPath}/resources/template/img/icons/Courses.png "
									alt="Ajouter un cours" title="Ajouter un cours"
									data-toggle="tooltip" data-placement="bottom" /></a></li>
						</sec:authorize>
						<li><a href="#" data-toggle="modal" data-target="#myModal"
							id="CoursesButton" class="item"> <c:if
									test="${UnseenCourse gt 0}">
									<span class="notify-badge number red">${UnseenCourse}</span>
								</c:if> <img
								src="${pageContext.servletContext.contextPath}/resources/template/img/icons/Courses.png "
								alt="Consulter les cours" title="Consulter les cours"
								data-toggle="tooltip" data-placement="bottom" /></a></li>
						<sec:authorize access="hasRole('ROLE_TEACHER')">
							<li><a href="#" data-toggle="modal" data-target="#myModal"
								id="AddExercicesButton" class="item"> <span
									class="notify-badge green glyphicon glyphicon-plus"></span><img
									src="${pageContext.servletContext.contextPath}/resources/template/img/icons/Exercices.png "
									alt="Ajouter un exercice" title="Ajouter un exercice"
									data-toggle="tooltip" data-placement="bottom" /></a></li>
						</sec:authorize>
						<li><a href="#" data-toggle="modal" data-target="#myModal"
							id="ExercicesButton"> <c:if test="${UnseenExercice gt 0}">
									<span class="notify-badge number red">${UnseenExercice}</span>
								</c:if> <img
								src="${pageContext.servletContext.contextPath}/resources/template/img/icons/Exercices.png "
								alt="Consulter les exercices" title="Consulter les exercices"
								data-toggle="tooltip" data-placement="bottom" /></a></li>

						<li><a href="#" data-toggle="modal" data-target="#myModal"
							id="MessageButton"><c:if test="${UnseenMessages gt 0}">
									<span class="notify-badge number red">${UnseenMessages}</span>
								</c:if> <img
								src="${pageContext.servletContext.contextPath}/resources/template/img/icons/Courses.png "
								alt="Messagerie" title="Messagerie" data-toggle="tooltip"
								data-placement="bottom" /></a></li>
						<li><a href="#"><img
								src="${pageContext.servletContext.contextPath}/resources/template/img/icons/Logout.png "
								alt="Logout" title="Logout" data-toggle="tooltip"
								data-placement="bottom" id="LogoutButton" /></a></li>

					</ul>
				</div>
			</div>
		</div>
		<div id="myModal" class="modal fade"></div>
		<c:url value="/logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>

	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

	<!--  <script src="//cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>-->
	<script src="${bootstrapJs}"></script>
	<script src="${jasnyJs}"></script>
	<script src="${DialogJs}"></script>
	<script src="${dataTablesJs}"></script>
	<script src="${dataTablesBootstrapJs}"></script>
	<script src="${dataTablesButtonsJs}"></script>
	<script src="${dataTablesBootstrapButtonsJs}"></script>
	<script src="${select2Js}"></script>
	<script src="${appJs}"></script>
</body>
</html>