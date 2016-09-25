<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div
	class="col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12 alert-window">
	<h4>
		<span> Message </span>
		<button type="button" class="close" data-dismiss="modal">&times;</button>
	</h4>

	<div class="alert-body col-md-12">
		<div class="col-md-12">
			<div class="input-group">
				<label for="to">De la part de: </label>
			</div>
			<span style="padding-left: 20px;"> ${message.from.username } </span>
			<span class="pull-right">${message.date } </span>
			<div class="input-group">

				<label for="Object">Objet: </label>
			</div>
			<span style="padding-left: 20px;"> ${message.object } </span>
			<div class="input-group">

				<label for="Message">Message: </label>
			</div>
			<p style="padding-left: 20px; min-height: 200px;">${message.body }</p>
		</div>
		<hr />
	</div>
</div>
