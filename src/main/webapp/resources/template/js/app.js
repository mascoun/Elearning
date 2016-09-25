$(document).ready(function() {
	var WebSiteURL = "http://127.0.0.1:8080/e-learning";
	var UploadURL = "http://127.0.0.1:8080/data";
	// Registration
	$('#UserType').on('change', function() {
		if ($(this).val() == "Teacher") {
			$('input[name="subject"]').show();
			$('input[name="subject"]').attr("required", "required");
		} else {
			$('input[name="subject"]').hide();
			$('input[name="subject"]').removeAttr("required", "required");
		}
	});
	// ADMIN
	$('.activateUser').each(function() {
		$(this).click(function() {
			$elem = $(this)
			$.ajax({
				type : "POST",
				url : WebSiteURL + "/user/verify/" + $(this).attr("data-id"),
				success : function(data) {
					BootstrapDialog.show({
						title : 'Supprimé !',
						message : 'Le Compte est Verifié',
						type : BootstrapDialog.TYPE_SUCCESS
					});
					$($elem).parent().parent().addClass('list-group-item-success');
				},
				error : function() {
					BootstrapDialog.show({
						title : 'Erreur',
						message : 'Un erreur rencontrée lors de la verification de ce compte',
						type : BootstrapDialog.TYPE_DANGER
					});
				}
			});
		});
	});
	$(".deleteUser").each(function() {
		$(this).click(function() {
			$elem = $(this);
			var dialog = new BootstrapDialog({
				title : 'Supprimer',
				message : 'voulez vous vraiment supprimer ce compte? ',
				type : BootstrapDialog.TYPE_WARNING,
				buttons : [ {
					label : 'Supprimer',
					cssClass : 'btn-danger',
					id : 'DeleteUserButton'
				}, {
					label : 'Annuler',
					cssClass : 'btn-default',
					action : function(dialog) {
						dialog.close();
					}
				} ]
			});
			dialog.realize();
			var DeleteCourseButton = dialog.getButton('DeleteUserButton');
			$(DeleteCourseButton).click({
				id : $(this).attr("data-id")
			}, function(event) {
				$.ajax({
					url : WebSiteURL + "/classe/delete/" + event.data.id,
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
								message : 'Compte Supprimé !',
								type : BootstrapDialog.TYPE_SUCCESS
							});
							$($elem).parent().parent().addClass('list-group-item-danger');
						} else {
							$('#myModal').modal('hide');
							BootstrapDialog.show({
								title : 'Erreur',
								message : 'Un erreur rencontrée lors de la suppression du compte.',
								type : BootstrapDialog.TYPE_DANGER
							});
						}
					}
				});
			});
			dialog.open();
		});
	});

	$('.DeleteClasse').each(function() {
		$(this).click(function() {
			$elem = $(this);
			var dialog = new BootstrapDialog({
				title : 'Supprimer',
				message : 'voulez vous vraiment supprimer ce classe? ',
				type : BootstrapDialog.TYPE_WARNING,
				buttons : [ {
					label : 'Supprimer',
					cssClass : 'btn-danger',
					id : 'DeleteClasseButton'
				}, {
					label : 'Annuler',
					cssClass : 'btn-default',
					action : function(dialog) {
						dialog.close();
					}
				} ]
			});
			dialog.realize();
			var DeleteCourseButton = dialog.getButton('DeleteClasseButton');
			$(DeleteCourseButton).click({
				id : $(this).attr("data-id")
			}, function(event) {
				$.ajax({
					url : WebSiteURL + "/classe/deleteClass/" + event.data.id,
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
								message : 'Classe Supprimé !',
								type : BootstrapDialog.TYPE_SUCCESS
							});
							$($elem).parent().parent().addClass('list-group-item-danger');
						} else {
							$('#myModal').modal('hide');
							BootstrapDialog.show({
								title : 'Erreur',
								message : 'Un erreur rencontrée lors de la suppression du classe.',
								type : BootstrapDialog.TYPE_DANGER
							});
						}
					}
				});
			});
			dialog.open();

		});
	});
	// Others
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
	$('#ExercicesButton').click(function() {
		Loading();
		Exercices();
	});
	$('#MessageButton').click(function() {
		Loading();
		Messages();
	});
	$('#AddCoursesButton').click(function() {
		Loading();
		$('#myModal').load(WebSiteURL + "/courses/new", function() {
			$('#title').text("Ajouter un cours");
			AddSubmiter();
		});
	});
	$('#AddExercicesButton').click(function() {
		Loading();
		$('#myModal').load(WebSiteURL + "/exercices/new", function() {
			$('#title').text("Ajouter un exercice");
			AddSubmiter();
		});
	});
	function AddSubmiter() {
		$("#fileInput").on("change", function() {
			$("#fileName").val($('#fileInput').val().split('\\').pop());
			var $form = $("#add");
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
					$('.fileinput-preview.thumbnail[data-trigger="fileinput"]').html("<img src='" + UploadURL + "/upload/images/" + data + ".jpg" + "' />");
					$('#link').val(UploadURL + "/upload/documents/" + data + ".pdf");
					$('#photo').val(UploadURL + "/upload/images/" + data + ".jpg");
				},
				complete : function(data) {
					console.log(data.responseText);
				}
			});
		});
		$('form#add').submit(function(e) {
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
	function ExercicesEditSubmiter() {
		$('.fileinput-preview.thumbnail').html("<img src='" + $('#photo').val() + "' />");
		$("#fileInput").on("change", function() {
			$("#fileName").val($('#fileInput').val().split('\\').pop());
			var $form = $("#add");
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
		$("form#add").submit(function(e) {
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
							message : 'Cliquer ici pour visionner l\'exercice',
							type : BootstrapDialog.TYPE_SUCCESS
						});
						Exercices();
					} else {
						$('#myModal').modal('hide');
						BootstrapDialog.show({
							title : 'Erreur',
							message : 'un erreur rencontrée lors de la modification de l\'exercice',
							type : BootstrapDialog.TYPE_DANGER
						});
					}
				}
			});
			return false;
		})

	}
	function CoursEditSubmiter() {
		$('.fileinput-preview.thumbnail').html("<img src='" + $('#photo').val() + "' />");
		$("#fileInput").on("change", function() {
			$("#fileName").val($('#fileInput').val().split('\\').pop());
			var $form = $("#add");
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
		$("form#add").submit(function(e) {
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
			dataTable();
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
							// window.location.href = $(elem).attr("href");
							$('#myModal').load(WebSiteURL + "/courses/" + $(elem).attr("data-id"));
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
										message : 'Un erreur rencontrée lors de la suppression du cours',
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
	function Exercices() {
		$('#myModal').load(WebSiteURL + "/exercices", function() {
			dataTable();
			$("[data-toggle='See']").each(function() {
				$(this).on('click', function(e) {
					// alert($(this).attr("data-id"));
					var elem = $(this);
					$.ajax({
						url : WebSiteURL + "/exercices/seen/" + $(this).attr("data-id"),
						data : {
							id : $(this).attr("data-id")
						},
						type : "PUT",
						headers : {
							"X-HTTP-Method-Override" : "PUT"
						},
						success : function(data) {
							// window.location.href = $(elem).attr("href");
							$('#myModal').load(WebSiteURL + "/exercices/" + $(elem).attr("data-id"));
						}
					});
					e.preventDefault();
				});
			});
			$('.EditExercicesButton').each(function() {
				$(this).on("click", function() {
					$('#myModal').load(WebSiteURL + "/exercices/edit?id=" + $(this).attr("data-id"), function() {
						$('#title').text("Modifier le exercice");
						ExercicesEditSubmiter();

					});
				});
			});
			$('.DeleteExercicesButton').each(function() {
				$(this).on("click", function() {
					var dialog = new BootstrapDialog({
						title : 'Supprimer',
						message : 'voulez vous vraiment supprimer cet exercice? ',
						type : BootstrapDialog.TYPE_WARNING,
						buttons : [ {
							label : 'Supprimer',
							cssClass : 'btn-danger',
							id : 'DeleteExerciceButton'
						}, {
							label : 'Annuler',
							cssClass : 'btn-default',
							action : function(dialog) {
								dialog.close();
							}
						} ]
					});
					dialog.realize();
					var DeleteCourseButton = dialog.getButton('DeleteExerciceButton');
					$(DeleteCourseButton).click({
						id : $(this).attr("data-id")
					}, function(event) {
						$.ajax({
							url : WebSiteURL + "/exercices/delete?id=" + event.data.id,
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
										message : 'Exercice Supprimé !',
										type : BootstrapDialog.TYPE_SUCCESS
									});
									Courses();
								} else {
									$('#myModal').modal('hide');
									BootstrapDialog.show({
										title : 'Erreur',
										message : 'Un erreur rencontrée lors de la suppression de l\'exercice',
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
	function Messages() {
		$('#myModal').load(WebSiteURL + "/messages", function() {
			var table = dataTable();
			/*
			 * new $.fn.dataTable.Buttons(table, { buttons : [ 'copy', 'excel',
			 * 'pdf' ] });
			 */
			table.buttons().container().appendTo($('.col-sm-6:eq(0)', table.table().container()));
			// /messages/seen/
			$('#Table').find('a[data-toggle="Mail"]').each(function() {
				$(this).on('click', function(e) {
					var elem = $(this);
					$.ajax({
						url : WebSiteURL + "/messages/seen/" + $(this).attr("data-id"),
						data : {
							id : $(this).attr("data-id")
						},
						type : "PUT",
						headers : {
							"X-HTTP-Method-Override" : "PUT"
						},
						success : function(data) {
							// window.location.href = $(elem).attr("href");
							$('#myModal').load(WebSiteURL + "/messages/" + $(elem).attr("data-id"));
						}
					});
					e.preventDefault();
					return false;
				});
			});
		});
	}

	function dataTable() {
		return $('#Table').DataTable({
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
			"bLengthChange" : false,
			buttons : [ {
				text : '<i class="glyphicon glyphicon-plus"></i> NOUVEAU MESSAGE',
				action : function(e, dt, node, config) {
					NewMessage();
				}
			} ]
		});
	}
	function NewMessage() {
		$('#myModal').load(WebSiteURL + "/messages/new", function() {
			NewMessageSubmitter();
		});
	}
	function NewMessageSubmitter() {
		$('#to').select2();
		$('#messageForm').submit(function(e) {
			e.preventDefault();
			$.ajax({
				url : $(this).attr("action"),
				data : $(this).serialize(),
				type : "POST",
				headers : {
					"X-HTTP-Method-Override" : "POST"
				},
				success : function(data) {
					if (data == "SUCCESS") {
						$('#myModal').modal('hide');
						BootstrapDialog.show({
							title : 'Envoyer !',
							message : 'Votre message est envoyé',
							type : BootstrapDialog.TYPE_SUCCESS
						});
					} else {
						$('#myModal').modal('hide');
						BootstrapDialog.show({
							title : 'Erreur',
							message : 'Un erreur rencontrée lors de l\'envoie du message',
							type : BootstrapDialog.TYPE_DANGER
						});
					}
				}
			});
			return false;
		});
	}
});