<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div
	class="col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12 alert-window">
	<h4>
		Liste des Cours
		<button type="button" class="close" data-dismiss="modal">&times;</button>
	</h4>

	<div class="alert-body">
		<table class="table" id="Table">
			<thead>
				<tr>
					<th>Nom du cours</th>
					<sec:authorize access="hasRole('ROLE_STUDENT')">
						<th class="hidden-xs">Matiere</th>
					</sec:authorize>
					<th class="hidden-xs hidden-sm">Description</th>
					<th class="hidden-xs">Date d'ajout</th>
					<sec:authorize access="hasRole('ROLE_TEACHER')">
						<th></th>
					</sec:authorize>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listeCourses}" var="course">
					<tr>
						<td><a data-id="${course.idCourse}" data-toggle="See"
							href="<c:out value="${course.link}"></c:out>"><c:out
									value="${course.name}" /></a></td>
						<sec:authorize access="hasRole('ROLE_STUDENT')">
							<td class="hidden-xs"><c:out
									value="${course.teacher.subject }"></c:out></td>
						</sec:authorize>
						<td class="hidden-xs hidden-sm"><c:out
								value="${course.description}"></c:out></td>
						<td class="hidden-xs"><c:out value="${course.date}" /></td>
						<sec:authorize access="hasRole('ROLE_TEACHER')">
							<td><a href="#" class="btn btn-primary EditCoursesButton"
								data-id="${course.idCourse}"> <span
									class="glyphicon glyphicon-edit"></span> <span
									class="hidden-xs"> Modifier </span>
							</a> <a href="#" class="btn btn-danger DeleteCoursesButton"
								data-id="${course.idCourse}"> <span
									class="glyphicon glyphicon-remove-sign"></span> <span
									class="hidden-xs"> Supprimer </span>
							</a></td>
						</sec:authorize>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

