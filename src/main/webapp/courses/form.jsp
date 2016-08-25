<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div
	class="col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12 alert-window">
	<h4>
		<span id="title"> </span>
		<button type="button" class="close" data-dismiss="modal">&times;</button>
	</h4>

	<div class="alert-body col-md-12">
		<sec:authorize access="hasRole('ROLE_TEACHER')">
			<form:form method="POST" modelAttribute="course">
				<div class="col-md-6">
					<div class="input-group">
						<label for="name">Nom du cours: </label>
					</div>
					<form:input class="form-control" required="true" type="text"
						path="name" id="name" />

					<div class="input-group">

						<label for="description">Description du cours: </label>
					</div>
					<form:textarea class="form-control" required="true"
						path="description" id="description"
						style="resize: none;height: 161px;" />
				</div>
				<div class="col-md-6">
					<div class="input-group">

						<label for="link">Document: </label>
					</div>
					<div class="fileinput fileinput-new" data-provides="fileinput"
						style="width: 100%;">
						<span class="btn btn-default btn-file hidden"><span
							class="fileinput-new hidden">Selectionner</span><span
							class="fileinput-exists hidden">Changer</span> <input type="file"
							name="" class=" form-control"
							accept="application/msword, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/pdf" /></span>
						<a href="#" class="btn btn-default fileinput-exists hidden"
							data-dismiss="fileinput">Remove</a> <br />
						<div class="fileinput-preview thumbnail" data-trigger="fileinput"
							style="height: 200px; width: 100%;"></div>
					</div>
				</div>
				<hr />
				<div class="col-md-12" style="text-align: center;">
					<form:input class="form-control hidden" required="true" path="link"
						id="link" value="www.google.com" />

					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" /> <input type="submit"
						class="btn btn-primary" />
				</div>
			</form:form>
		</sec:authorize>
	</div>
</div>
