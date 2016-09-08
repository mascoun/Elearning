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
		$('#myModal').html("<div style='display: flex;align-items: center;justify-content: center;height: 100vh;'><i class='glyphicon glyphicon-refresh gly-spin' style='font-size: 40px;color: aliceblue;'></i></div>");
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
			var $form = $("#course");
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
					$('#link').val(WebSiteURL + "/upload/documents/" + data + ".pdf");
					$('#photo').val(WebSiteURL + "/upload/images/" + data + ".jpg");
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
		$('.fileinput-preview.thumbnail').html("<img src='" + $('#photo').val() + "' />");
		$("#fileInput").on("change", function() {
			$("#fileName").val($('#fileInput').val().split('\\').pop());
			var $form = $("#course");
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
					$('#link').val(WebSiteURL + "/upload/documents/" + data + ".pdf");
					$('#photo').val(WebSiteURL + "/upload/images/" + data + ".jpg");
				},
				complete : function(data) {
					console.log(data.responseText);
				}
			});
		});
		$("form#course").submit(function(e) {
			e.preventDefault();
			$.ajax({
				url : $(this).attr("action"),
				data : $(this).serialize(),
				type : "PUT",
				headers : {
					"X-HTTP-Method-Override" : "PUT"
				},
				success : function(data) {
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
				}
			});
			return false;
		})
	}
	function Courses() {
		$('#myModal').load(WebSiteURL + "/courses", function() {
			$('#Table').DataTable({
				"language" : {
					"sProcessing" : "Traitement en cours...",
					"sSearch" : "Rechercher&nbsp;:",
					"sLengthMenu" : "Afficher _MENU_ &eacute;l&eacute;ments",
					"sInfo" : "Affichage de l'&eacute;l&eacute;ment _START_ &agrave; _END_ sur _TOTAL_ &eacute;l&eacute;ments",
					"sInfoEmpty" : "Affichage de l'&eacute;l&eacute;ment 0 &agrave; 0 sur 0 &eacute;l&eacute;ment",
					"sInfoFiltered" : "(filtr&eacute; de _MAX_ &eacute;l&eacute;ments au total)",
					"sInfoPostFix" : "",
					"sLoadingRecords" : "Chargement en cours...",
					"sZeroRecords" : "Aucun &eacute;l&eacute;ment &agrave; afficher",
					"sEmptyTable" : "Aucune donn&eacute;e disponible dans le tableau",
					"oPaginate" : {
						"sFirst" : "Premier",
						"sPrevious" : "Pr&eacute;c&eacute;dent",
						"sNext" : "Suivant",
						"sLast" : "Dernier"
					},
					"oAria" : {
						"sSortAscending" : ": activer pour trier la colonne par ordre croissant",
						"sSortDescending" : ": activer pour trier la colonne par ordre d&eacute;croissant"
					}
				},
				"pageLength" : 5,
				"bInfo" : false,
				// "bFilter" : false,
				"bLengthChange" : false
			});
			$("[data-toggle='See']").each(function() {
				$(this).on('click', function(e) {
					// alert($(this).attr("data-id"));
					var elem = $(this);
					$.ajax({
						url : WebSiteURL + "/courses/seen/" + $(this).attr("data-id"),
						data : {
							id : $(this).attr("data-id")
						},
						type : "PUT",
						headers : {
							"X-HTTP-Method-Override" : "PUT"
						},
						success : function(data) {
							window.location.href = $(elem).attr("href");
						}
					});
					e.preventDefault();
				});
			});
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
							type : "DELETE",
							headers : {
								"X-HTTP-Method-Override" : "DELETE"
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