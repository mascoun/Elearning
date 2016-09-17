<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div
	class="col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12 alert-window">
	<h4>
		<span> Nouveau message </span>
		<button type="button" class="close" data-dismiss="modal">&times;</button>
	</h4>

	<div class="alert-body col-md-12">
		<form:form method="POST" modelAttribute="message" id="messageForm">
			<div class="col-md-12">
				<div class="input-group">
					<label for="to">À: </label>
				</div>
				<select aria-hidden="true" multiple="multiple" class="form-control"
					name="to" id="to">
					<c:forEach items="${users}" var="user">
						<option value="${user.id}">${user.username}</option>
					</c:forEach>
				</select>

				<div class="input-group">

					<label for="Object">Objet: </label>
				</div>
				<form:input class="form-control" required="true" type="text"
					path="object" />
				<div class="input-group">

					<label for="Message">Message: </label>
				</div>
				<form:textarea class="form-control" required="true" path="body"
					style="resize: none;height: 161px;" />
			</div>
			<hr />
			<div class="col-md-12" style="text-align: center;">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /> <input type="submit"
					class="btn btn-primary" />
			</div>
		</form:form>
	</div>
</div>
