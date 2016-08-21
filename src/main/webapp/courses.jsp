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
		<table class="table">
			<thead>
				<tr>
					<th>Nom du cours</th>
					<th>Matiere</th>
					<th>Date d'ajout</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Chapite 1 : Les Pointeurs</td>
					<td>Algorithme</td>
					<td>10 Mars 2010</td>
				</tr>
				<c:forEach items="${listeCourses}" var="course">
					<tr>
						<td><c:out value="${course.id}" /></td>
						<td><a href="<c:out value="${course.link}"></c:out>"> <c:out
									value="${course.name}"></c:out></a></td>
						<td><c:out value="${course.date}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="flex">
			<ul class="pagination pagination-custom">
				<li class="active"><a href="#">1</a></li>
				<li><a href="#">2</a></li>
			</ul>
		</div>
	</div>
</div>

