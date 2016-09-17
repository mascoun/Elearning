package com.ensi.project.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ensi.project.model.Classe;
import com.ensi.project.model.Course;
import com.ensi.project.model.Exercice;
import com.ensi.project.model.Student;
import com.ensi.project.model.Teacher;
import com.ensi.project.model.User;
import com.ensi.project.service.CourseService;
import com.ensi.project.service.ExerciceService;
import com.ensi.project.service.UserService;

@Controller
public class HomeController {
	@Autowired
	CourseService courseService;

	@Autowired
	ExerciceService exerciceService;

	@Autowired
	UserService userService;

	@RequestMapping(value = { "/", "/home**" }, method = RequestMethod.GET)
	public String homePage(HttpServletRequest request, ModelMap pModel) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			if (request.isUserInRole("ROLE_ADMIN")) {
				return "redirect:/admin";
			} else {
				User user = userService.getUserByUsername(
						((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername());
				List<Course> ListeCourses = new ArrayList<>();
				List<Exercice> ListeExercices = new ArrayList<>();
				if (user instanceof Student) {
					Student student = (Student) user;
					Classe classe = student.getClasse();
					if (classe != null) {
						List<Teacher> teachers = new ArrayList<>(classe.getTeachers());

						for (int i = 0; i < teachers.size(); i++) {
							ListeCourses.addAll(courseService.getAllCoursesByTeacher(teachers.get(i)));
						}
						ListeCourses = courseService.getAllCourses();
						ListeExercices = exerciceService.getAllExercices();
						List<Course> ListeSeenCourses = new ArrayList<>();
						List<Exercice> ListeSeenExercices = new ArrayList<>();
						ListeSeenCourses.addAll(courseService.getSeenCoursesByStudent(student));
						ListeSeenExercices.addAll(exerciceService.getSeenExercicesByStudent(student));
						pModel.addAttribute("UnseenCourse", (int) (ListeCourses.size() - ListeSeenCourses.size()));
						pModel.addAttribute("UnseenExercice",
								(int) (ListeExercices.size() - ListeSeenExercices.size()));
					}
				}
				return "home";
			}
		} else {
			return "redirect:login";
		}

	}
}
