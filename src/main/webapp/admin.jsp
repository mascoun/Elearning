<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	<div class="row">
		<div
			class="col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12 alert-window">
			<h4>
				Administration
				<button type="button" class="close" data-toggle="tooltip"
					data-placement="bottom" title="Logout" id="LogoutButton">
					<img
						src="${pageContext.servletContext.contextPath}/resources/template/img/icons/Logout.png "
						alt="Logout" style="height: 25px; width: 25px;" />
				</button>
			</h4>

			<div class="alert-body">
				<div class="row">
					<div class="col-md-6">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<span class="glyphicon glyphicon-list"></span>Liste des classes
							</div>
							<div class="panel-body">
								<ul class="list-group">
									<c:if test="${fn:length(listeClasses) eq 0}">
										<li class="list-group-item">
											<div class="text-center">Aucune classe existe</div>
										</li>


									</c:if>
									<c:forEach items="${listeClasses}" var="classe">

										<li class="list-group-item">
											<div class="checkbox">
												<a
													href="${pageContext.servletContext.contextPath}/admin/classe/${classe.classeId}"><label
													for="checkbox">${classe.name}</label></a>
											</div>
											<div class="pull-right action-buttons">
												<a href="#" class="EditClasse" data-id="${classe.classeId}"><span
													class="glyphicon glyphicon-pencil"></span></a> <a href="#"
													class="trash DeleteClasse" data-id="${classe.classeId}"><span
													class="glyphicon glyphicon-trash"></span></a>
											</div>
										</li>
									</c:forEach>

								</ul>
							</div>
							<div class="panel-footer">
								<div class="row">
									<div class="col-md-12">
										<form:form method="POST" modelAttribute="classe"
											action="${pageContext.servletContext.contextPath}/classe/addClass">
											<label>Nom du classe :</label>
											<form:input path="name" class="form-control"
												style="width:50%;display:inline;" />
											<input type="submit" class="btn btn-primary"
												value="Ajouter !" />
										</form:form>
									</div>

								</div>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<span class="glyphicon glyphicon-list"></span>Liste des comptes
								non activés
							</div>
							<div class="panel-body">
								<ul class="list-group">
									<c:if test="${fn:length(listeUsers) eq 0}">
										<li class="list-group-item">
											<div class="text-center">Aucun nouveau utilisateur</div>
										</li>


									</c:if>
									<c:forEach items="${listeUsers}" var="user">

										<li class="list-group-item">
											<div class="checkbox">
												<a href="#"><label for="checkbox">${user.username}</label></a>
											</div>
											<div class="pull-right action-buttons">
												<a href="#" class="activateUser" data-id="${user.id }"><span
													class="glyphicon glyphicon-check"></span></a> <a href="#"
													class="deleteUser trash" data-id="${user.id }"><span
													class="glyphicon glyphicon-trash"></span></a>
											</div>
										</li>
									</c:forEach>

								</ul>
							</div>
							<!-- <div class="panel-footer">
							<div class="row">
								<div class="col-md-6">
									<h6>
										Total Count <span class="label label-info">25</span>
									</h6>
								</div>
								<div class="col-md-6">
									<ul class="pagination pagination-sm pull-right">
										<li class="disabled"><a href="javascript:void(0)">«</a></li>
										<li class="active"><a href="javascript:void(0)">1 <span
												class="sr-only">(current)</span></a></li>
										<li><a href="http://www.jquery2dotnet.com">2</a></li>
										<li><a href="http://www.jquery2dotnet.com">3</a></li>
										<li><a href="http://www.jquery2dotnet.com">4</a></li>
										<li><a href="http://www.jquery2dotnet.com">5</a></li>
										<li><a href="javascript:void(0)">»</a></li>
									</ul>
								</div>
							</div>
						</div>-->
						</div>
					</div>
					<div class="col-md-12" style="height: 50px;"></div>




					<div class="col-md-12" style="height: 50px;">

						<c:url value="/logout" var="logoutUrl" />

						<form action="${logoutUrl}" method="post" id="logoutForm">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${flatuiJs}"></script>
	<script src="${jasnyJs}"></script>
	<script src="${DialogJs}"></script>
	<script src="${appJs}"></script>
</body>
</html>