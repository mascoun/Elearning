$(document).ready(function() {
	var WebSiteURL = "http://127.0.0.1:8080/e-learning";

	if ($.multiselect) {
		$('#search').multiselect({
			search : {
				left : '<input type="text" name="q" autocomplete="off"" class="form-control" placeholder="Recherche..." />',
				right : '<input type="text" name="q" autocomplete="off" class="form-control" placeholder="Recherche..." />',
			}
		});
		$('#search_st').multiselect({
			search : {
				left : '<input type="text" name="q" autocomplete="off" class="form-control" placeholder="Recherche..." />',
				right : '<input type="text" name="q" autocomplete="off" class="form-control" placeholder="Recherche..." />',
			}
		});
	}
	function Loading() {
		$('#myModal').html("a");
	}
	$('[data-toggle="tooltip"]').tooltip()
	$('#LogoutButton').click(function() {
		$('#logoutForm').submit();
	});
	$('#CoursesButton').click(function() {
		Loading();
		Courses();
	});
	$('#AddCoursesButton').click(function() {
		Loading();
		$('#myModal').load(WebSiteURL + "/courses/new", function() {
			$('#title').text("Ajouter un cours");
			CoursAddSubmiter();
		});
	});
	function CoursAddSubmiter() {
		$("#fileInput").on("change", function() {
			$("#fileName").val($('#fileInput').val().split('\\').pop());
			var $form = $("#fmUpload");
			var formdata = (window.FormData) ? new FormData($form[0]) : null;
			var data = (formdata !== null) ? formdata : $form.serialize();

			console.log(data);
			$.ajax({
				type : "POST",
				// contentType : $("#fmUpload").attr("enctype",
				// "multipart/form-data"),
				contentType : false, // obligatoire pour de l'upload
				processData : false, // obligatoire pour de l'upload
				url : WebSiteURL + "/uploadFile",
				data : data,
				success : function(data) {
					$('.fileinput-preview.thumbnail[data-trigger="fileinput"]').html("<img src='" + WebSiteURL + "/upload/images/" + data + ".jpg" + "' />");
				},
				complete : function(data) {
					console.log(data.responseText);
				}
			});
		});
		$("form#course").submit(function(e) {
			e.preventDefault();
			$.post($(this).attr("action"), $(this).serialize()).done(function(data) {
				if (data == "SUCCESS") {
					$('#myModal').modal('hide');
					BootstrapDialog.show({
						title : 'Cours ajouté !',
						message : 'Cliquer ici pour visionner le cours',
						type : BootstrapDialog.TYPE_SUCCESS
					});
				} else {
					$('#myModal').modal('hide');
					BootstrapDialog.show({
						title : 'Erreur',
						message : 'un erreur rencontrée lors de l\'ajout du cours',
						type : BootstrapDialog.TYPE_DANGER
					});
				}
			});
			return false;
		})
	}
	function CoursEditSubmiter() {
		$("form#course").submit(function(e) {
			e.preventDefault();
			$.post($(this).attr("action"), $(this).serialize()).done(function(data) {
				if (data == "SUCCESS") {
					// $('#myModal').modal('hide');
					BootstrapDialog.show({
						title : 'Cours Modifié !',
						message : 'Cliquer ici pour visionner le cours',
						type : BootstrapDialog.TYPE_SUCCESS
					});
					Courses();
				} else {
					$('#myModal').modal('hide');
					BootstrapDialog.show({
						title : 'Erreur',
						message : 'un erreur rencontrée lors de la modification du cours',
						type : BootstrapDialog.TYPE_DANGER
					});
				}
			});
			return false;
		})
	}
	function Courses() {
		$('#myModal').load(WebSiteURL + "/courses", function() {
			$('.EditCoursesButton').each(function() {
				$(this).on("click", function() {
					$('#myModal').load(WebSiteURL + "/courses/edit?id=" + $(this).attr("data-id"), function() {
						$('#title').text("Modifier le cours");
						CoursEditSubmiter();
					});
				});
			});
			$('.DeleteCoursesButton').each(function() {
				$(this).on("click", function() {
					var dialog = new BootstrapDialog({
						title : 'Supprimer',
						message : 'voulez vous vraiment supprimer ce cours? ',
						type : BootstrapDialog.TYPE_WARNING,
						buttons : [ {
							label : 'Supprimer',
							cssClass : 'btn-danger',
							id : 'DeleteCourseButton'
						}, {
							label : 'Annuler',
							cssClass : 'btn-default',
							action : function(dialog) {
								dialog.close();
							}
						} ]
					});
					dialog.realize();
					var DeleteCourseButton = dialog.getButton('DeleteCourseButton');
					$(DeleteCourseButton).click({
						id : $(this).attr("data-id")
					}, function(event) {
						$.ajax({
							url : WebSiteURL + "/courses/delete?id=" + event.data.id,
							data : {
								id : event.data.id
							},
							type : "GET",
							headers : {
								"X-HTTP-Method-Override" : "GET"
							},
							success : function(data) {
								dialog.close();
								if (data == "SUCCESS") {
									BootstrapDialog.show({
										title : 'Supprimer',
										message : 'Cours Supprimé !',
										type : BootstrapDialog.TYPE_SUCCESS
									});
									Courses();
								} else {
									$('#myModal').modal('hide');
									BootstrapDialog.show({
										title : 'Erreur',
										message : 'un erreur rencontrée lors de la suppression du cours',
										type : BootstrapDialog.TYPE_DANGER
									});
								}
							}
						});
					});
					dialog.open();
				});
			});
			$('.pagination  li').each(function() {
				$(this).on('click', function() {
					$('.pagination  li').each(function() {
						$(this).removeClass('active');
					});
					$(this).addClass('active');
				});
			});

		});
	}
});