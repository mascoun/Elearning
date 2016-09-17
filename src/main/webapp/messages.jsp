<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div
	class="col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12 alert-window">
	<h4>
		Messagerie
		<button type="button" class="close" data-dismiss="modal">&times;</button>
	</h4>

	<div class="alert-body">
		<table class="table" id="Table">
			<thead>
				<tr>
					<th>De la part de</th>
					<th class="hidden-xs hidden-sm">Objet</th>
					<th class="hidden-xs">Date</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${messages}" var="message">
					<tr>
						<td><a data-id="${message.idMessage}" data-toggle="Mail"
							href="<c:out value=""></c:out>"><c:out
									value="${message.from.username}" /></a></td>
						<td class="hidden-xs"><c:out value="${message.object }"></c:out></td>
						<td class="hidden-xs hidden-sm"><c:out
								value="${message.date}"></c:out></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

