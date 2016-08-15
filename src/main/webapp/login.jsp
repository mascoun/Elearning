<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
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
		<div class="card card-container">
			<!-- <img class="profile-img-card" src="//lh3.googleusercontent.com/-6V8xOA6M7BA/AAAAAAAAAAI/AAAAAAAAAAA/rzlHcD0KYwo/photo.jpg?sz=120" alt="" /> -->
		<!-- 	<img id="profile-img" class="profile-img-card"
				src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" />
			<p id="profile-name" class="profile-name-card"></p>  -->
			<c:if test="${not empty error}">
				<div class="alert alert-danger">${error}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="alert alert-info">${msg}</div>
			</c:if>
			<form name='loginForm'
				action="<c:url value='/login'></c:url>"
				method='POST' class="form-signin">
				<span id="reauth-email" class="reauth-email"></span> <input
					type="text" name="username" id="inputUsername" class="form-control"
					placeholder="Username" required autofocus> <input
					type="password" name="password" id="inputPassword"
					class="form-control" placeholder="Password" required>
				<div id="remember" class="checkbox">
					<label> <input type="checkbox" value="remember-me">
						Remember me
					</label>
				</div>
				<button class="btn btn-lg btn-primary btn-block btn-signin"
					type="submit">Sign in</button>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
			<!-- /form -->
			<a href="#" class="forgot-password"> Forgot the password? </a>
		</div>
		<!-- /card-container -->
	</div>
	<!-- /container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${bootstrapJs}"></script>
	<script src="${flatuiJs}"></script>
</body>
</html>