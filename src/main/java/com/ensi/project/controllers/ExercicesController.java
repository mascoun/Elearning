package com.ensi.project.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ensi.project.model.Classe;
import com.ensi.project.model.Exercice;
import com.ensi.project.model.SeenExercice;
import com.ensi.project.model.Student;
import com.ensi.project.model.Teacher;
import com.ensi.project.model.User;
import com.ensi.project.service.ExerciceService;
import com.ensi.project.service.UserService;

@Controller
public class ExercicesController {
	@Autowired
	ExerciceService exerciceService;

	@Autowired
	UserService userService;

	@RequestMapping(value = { "/exercices" }, method = RequestMethod.GET)
	public String ExercicesPage(final ModelMap pModel) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			User user = userService.getUserByUsername(
					((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername());
			List<Exercice> ListeExercices = new ArrayList<>();
			if (user instanceof Student) {
				Student student = (Student) user;
				Classe classe = student.getClasse();
				if (classe != null) {
					List<Teacher> teachers = new ArrayList<>(classe.getTeachers());

					for (int i = 0; i < teachers.size(); i++) {
						ListeExercices.addAll(exerciceService.getAllExercicesByTeacher(teachers.get(i)));
					}
					ListeExercices = exerciceService.getAllExercices();
				}
			} else if (user instanceof Teacher) {
				Teacher teacher = (Teacher) user;
				ListeExercices = exerciceService.getAllExercicesByTeacher(teacher);
			}
			pModel.addAttribute("listeExercice", ListeExercices);
			return "exercices";
		} else {
			return "redirect:/login";
		}

	}

	@RequestMapping(value = { "/exercices/{id}" }, method = RequestMethod.GET)
	public String viewCourse(@PathVariable(value = "id") int id, final ModelMap pModel) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			Exercice exercice = exerciceService.getExerciceById(id);
			pModel.addAttribute("document", exercice);
			return "view";
		} else {
			return "redirect:/login";
		}

	}

	@RequestMapping(value = { "/exercices/new" }, method = RequestMethod.GET)
	public String newExercice(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			Exercice exercice = new Exercice();
			model.addAttribute("exercice", exercice);
			// UserDetails userDetails = (UserDetails) auth.getDetails();
			// model.addAttribute("user", user);
			return "form/exercice";
		} else {
			return "ajax/error";
		}
	}

	@RequestMapping(value = { "/exercices/new" }, method = RequestMethod.POST)
	public String saveExercice(@Valid Exercice exercice, BindingResult result, ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			if (result.hasErrors()) {
				return "ajax/error";
			}
			User user = userService.getUserByUsername(
					((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername());
			exercice.setTeacher((Teacher) user);
			exercice.setDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
			exerciceService.createExercice(exercice);
		} else {
			return "ajax/error";
		}
		return "ajax/success";
	}

	@RequestMapping(value = { "/exercices/edit" }, method = RequestMethod.GET)
	public String editExercice(ModelMap model, @RequestParam(value = "id") int id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			Exercice exercice = exerciceService.getExerciceById(id);
			if (exercice != null)
				model.addAttribute("exercice", exercice);
			else
				return "ajax/error";
			return "form/exercice";
		} else {
			return "ajax/error";
		}
	}

	@RequestMapping(value = { "/exercices/edit" }, method = RequestMethod.PUT)
	@ResponseBody
	public String modifyExercice(@Valid Exercice exercice, BindingResult result, ModelMap model,
			@RequestParam(value = "id", required = true) int id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			if (result.hasErrors()) {
				return "ERROR";
			}

			User user = userService.getUserByUsername(
					((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername());
			exercice.setTeacher((Teacher) user);
			exercice.setDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
			exercice.setIdExercice(id);
			exerciceService.updateExercice(exercice);
		} else {
			return "ERROR";
		}
		return "SUCCESS";
	}

	@RequestMapping(value = { "/exercices/delete" }, method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteExercice(ModelMap model, @RequestParam(value = "id", required = true) int id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			exerciceService.deleteExercice(exerciceService.getExerciceById(id));
			if (exerciceService.getExerciceById(id) != null) {
				return "ERROR";
			}
		} else {
			return "ERROR";
		}
		return "SUCCESS";
	}

	@RequestMapping(value = { "/exercices/seen/{id}" }, method = RequestMethod.PUT)
	@ResponseBody
	public String SeeExercice(@PathVariable(value = "id") int id, ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			User user = userService.getUserByUsername(
					((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername());
			if (user instanceof Student) {
				if (!exerciceService.hasSeenExercice(exerciceService.getExerciceById(id), (Student) user)) {
					SeenExercice seenExercice = new SeenExercice();
					seenExercice.setExercice(exerciceService.getExerciceById(id));
					seenExercice.setStudent((Student) user);
					exerciceService.SeenExercice(seenExercice);
				}
			}
		} else {
			return "ERROR";
		}
		return "SUCCESS";
	}
}
