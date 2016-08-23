<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div
	class="col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12 alert-window">
	<h4>
		Ajouter un cours
		<button type="button" class="close" data-dismiss="modal">&times;</button>
	</h4>

	<div class="alert-body">
		<sec:authorize access="hasRole('ROLE_TEACHER')">
			<form:form method="POST" modelAttribute="course">
				<table>
					<tr>
						<td><label for="name">name: </label></td>
						<td><form:input type="text" path="name" id="name" /></td>
					</tr>
					<tr>
						<td><label for="description">description: </label></td>
						<td><form:input type="text" path="description"
								id="description" /></td>
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
						<td><input type="hidden" path="teacher" value="${user.id}" /> <input
							type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" /> <input type="submit" /></td>
					</tr>
				</table>
			</form:form>
		</sec:authorize>
	</div>
</div>
