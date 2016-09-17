<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div
	class="col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12 alert-window">
	<h4>
		${document.name}
		<button type="button" class="close" data-dismiss="modal">&times;</button>
	</h4>

	<div class="alert-body">
		<div class="col-md-12">
			<object data="${document.link}" type="application/pdf">
				alt : <a href="${document.link}">${document.name}</a>
			</object>
		</div>
	</div>
</div>

