package com.ensi.project.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ensi.project.model.Classe;
import com.ensi.project.model.Student;
import com.ensi.project.model.Teacher;
import com.ensi.project.service.ClasseService;
import com.ensi.project.service.UserService;

@Controller
public class AdminController {
	@Autowired
	ClasseService classeService;

	@Autowired
	UserService userService;

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(final ModelMap pModel) {
		Classe classe = new Classe();
		final List<Classe> ListeClasses;
		ListeClasses = classeService.getAllClasses();
		pModel.addAttribute("classe", classe);
		pModel.addAttribute("listeClasses", ListeClasses);
		return "admin";
	}

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);

			model.addObject("username", userDetail.getUsername());

		}

		model.setViewName("403");
		return model;

	}

	@RequestMapping(value = "/admin/addClass", method = RequestMethod.POST)
	public String addClasse(@Valid Classe classe, ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			classeService.createClasse(classe);
			return "redirect:/admin";
		} else {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/admin/classe/{id}", method = RequestMethod.GET)
	public String addClasse(@PathVariable(value = "id") int id, ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			Classe classe = classeService.getClasseById(id);
			Set<Student> ListeStudent = classe.getStudents();
			Set<Teacher> ListeTeacher = classe.getTeachers();
			List<Student> NoAffectedStudents = userService.getNoAffectedStudents();
			List<Teacher> AllTeachers = userService.getAllTeachersNotIn(classe);
			model.addAttribute("classe", classe);
			model.addAttribute("listeStudent", ListeStudent);
			model.addAttribute("listeTeacher", ListeTeacher);
			model.addAttribute("noAffectedStudents", NoAffectedStudents);
			model.addAttribute("allTeachers", AllTeachers);
			return "classe";
		} else {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/admin/classe/{id}", method = RequestMethod.POST)
	public String updateClasse(@PathVariable(value = "id") int id,
			@RequestParam(value = "noAffectedStudents", required = false) List<String> NoAffectedStudents,
			@RequestParam(value = "listeStudent", required = false) List<String> AffectedStudents,
			@RequestParam(value = "allTeachers", required = false) List<String> NoAffectedTeacher,
			@RequestParam(value = "listeTeacher", required = false) List<String> AffectedTeacher, ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			Classe classe = classeService.getClasseById(id);
			Set<Student> students = new HashSet<Student>();
			if (NoAffectedStudents != null && AffectedStudents.size() != 0)
				for (int i = 0; i < NoAffectedStudents.size(); i++) {
					Student student = (Student) userService.getUserById(Integer.parseInt(NoAffectedStudents.get(i)));
					student.setClasse(null);
					userService.update(student);
				}
			if (AffectedStudents != null && AffectedStudents.size() != 0)
				for (int i = 0; i < AffectedStudents.size(); i++) {
					Student student = (Student) userService.getUserById(Integer.parseInt(AffectedStudents.get(i)));
					students.add(student);
					student.setClasse(classeService.getClasseById(id));
					userService.update(student);
				}
			Set<Teacher> teachers = new HashSet<Teacher>();

			/*
			 * if (NoAffectedTeacher != null && NoAffectedTeacher.size() != 0)
			 * for (int i = 0; i < NoAffectedTeacher.size(); i++) { Teacher
			 * teacher = (Teacher)
			 * userService.getUserById(Integer.parseInt(NoAffectedTeacher.get(i)
			 * )); teachers.remove(teacher); // Set<Classe> classes =
			 * teacher.getClasses(); //
			 * classes.remove(classeService.getClasseById(id)); ///
			 * teacher.setClasses(classes); // userService.update(teacher);
			 * 
			 * }
			 */
			if (AffectedTeacher != null && AffectedTeacher.size() != 0)
				for (int i = 0; i < AffectedTeacher.size(); i++) {
					Teacher teacher = (Teacher) userService.getUserById(Integer.parseInt(AffectedTeacher.get(i)));
					teachers.add(teacher);
					// classes.add(classeService.getClasseById(id));
					// teacher.setClasses(classes);
					// userService.update(teacher);
				}
			//classe.setStudents(students);
			classe.setTeachers(teachers);
			classeService.updateClasse(classe);
			return "redirect:/admin/classe/{id}";
		} else {
			return "redirect:/login";
		}
	}
}
