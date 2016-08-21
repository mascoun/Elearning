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
import com.ensi.project.model.User;
import com.ensi.project.service.CourseService;

@Controller
public class CoursesController {
	@Autowired
	CourseService courseService;

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
		Course course = new Course();
		model.addAttribute("course", course);
		return "newcourse";
	}

	@RequestMapping(value = { "/courses/new" }, method = RequestMethod.POST)
	public String saveCourse(@Valid Course course, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "ajax/error";
		}
		courseService.createCourse(course);
		return "ajax/success";
	}

}
