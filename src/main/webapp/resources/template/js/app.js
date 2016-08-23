$(document).ready(function() {
	var WebSiteURL = "http://127.0.0.1:8080/e-learning";
	$('[data-toggle="tooltip"]').tooltip()
	$('#LogoutButton').click(function() {
		$('#logoutForm').submit();
	});
	$('#CoursesButton').click(function() {
		$('#myModal').load(WebSiteURL + "/courses", function() {

			$('.pagination  li').each(function() {
				$(this).on('click', function() {
					$('.pagination  li').each(function() {
						$(this).removeClass('active');
					});
					$(this).addClass('active');
				});
			});

		});
	});
	$('#AddCoursesButton').click(function() {
		$('#myModal').load(WebSiteURL + "/courses/new");
	});
});