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
<spring:url value="/resources/template/js/multiselect.min.js"
	var="multiselectJs" />
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
	<div class="container">
		<form:form method="POST">
			<div class="row">
				<div class="col-sm-5 col-xs-12">
					<h2 class="text-center">Liste des étudiants non affecté</h2>
					<select name="noAffectedStudents" id="search_st" size="13"
						class="form-control" multiple="multiple">
						<c:forEach items="${noAffectedStudents}" var="student">
							<option value="${student.id}">${student.username}</option>
						</c:forEach>
					</select>
				</div>

				<div class="col-sm-2 col-xs-12">
					<button type="button" id="search_st_rightSelected"
						class="btn btn-default btn-block">
						<i class="glyphicon glyphicon-chevron-right"></i>
					</button>
					<button type="button" id="search_st_leftSelected"
						class="btn btn-default btn-block">
						<i class="glyphicon glyphicon-chevron-left"></i>
					</button>
				</div>

				<div class="col-sm-5 col-xs-12">
					<h2 class="text-center">Liste des étudiants en ${classe.name}</h2>
					<select name="listeStudent" id="search_st_to" size="13"
						class="form-control" multiple="multiple">
						<c:forEach items="${listeStudent}" var="student">
							<option value="${student.id}">${student.username}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="row">
				<div class="col-xs-5">
					<h2 class="text-center">Liste des enseignants</h2>
					<select name="allTeachers" id="search" size="13"
						class="form-control" multiple="multiple">
						<c:forEach items="${allTeachers}" var="teacher">
							<option value="${teacher.id}">${teacher.username}</option>
						</c:forEach>
					</select>
				</div>

				<div class="col-xs-2">
					<button type="button" id="search_rightSelected"
						class="btn btn-default btn-block">
						<i class="glyphicon glyphicon-chevron-right"></i>
					</button>
					<button type="button" id="search_leftSelected"
						class="btn btn-default btn-block">
						<i class="glyphicon glyphicon-chevron-left"></i>
					</button>
				</div>

				<div class="col-xs-5">
					<h2 class="text-center">Liste des enseignants en
						${classe.name}</h2>
					<select name="listeTeacher" id="search_to" size="13"
						class="form-control" multiple="multiple">
						<c:forEach items="${listeTeacher}" var="teacher">
							<option value="${teacher.id}">${teacher.username}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<input type="submit" class="btn btn-primary" />
		</form:form>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${flatuiJs}"></script>
	<script src="${jasnyJs}"></script>
	<script src="${DialogJs}"></script>
	<script src="${multiselectJs}"></script>
	<script src="${appJs}"></script>
</body>
</html>