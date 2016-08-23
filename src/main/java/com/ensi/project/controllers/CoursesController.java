package com.ensi.project.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ensi.project.model.Course;
import com.ensi.project.model.Teacher;
import com.ensi.project.model.User;
import com.ensi.project.service.CourseService;
import com.ensi.project.service.UserService;

@Controller
public class CoursesController {
	@Autowired
	CourseService courseService;

	@Autowired
	UserService userService;

	@RequestMapping(value = { "/courses" }, method = RequestMethod.GET)
	public String CoursesPage(final ModelMap pModel) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			final List<Course> ListeCourses = courseService.getAllCourses();
			pModel.addAttribute("listeCourses", ListeCourses);
			return "courses";
		} else {
			return "redirect:login";
		}

	}

	@RequestMapping(value = { "/courses/new" }, method = RequestMethod.GET)
	public String newCourse(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			Course course = new Course();
			model.addAttribute("course", course);
			// UserDetails userDetails = (UserDetails) auth.getDetails();
			// model.addAttribute("user", user);
			return "newcourse";
		} else {
			return "ajax/error";
		}
	}

	@RequestMapping(value = { "/courses/new" }, method = RequestMethod.POST)
	public String saveCourse(@Valid Course course, BindingResult result, ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (result.hasErrors()) {
			return "ajax/error";
		}
		User user = userService.getUserByUsername(
				((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername());
		course.setTeacher((Teacher) user);
		courseService.createCourse(course);
		return "ajax/success";
	}

}
