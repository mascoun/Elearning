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
import com.ensi.project.model.Course;
import com.ensi.project.model.SeenCourse;
import com.ensi.project.model.Student;
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
			User user = userService.getUserByUsername(
					((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername());
			List<Course> ListeCourses = new ArrayList<>();
			if (user instanceof Student) {
				Student student = (Student) user;
				Classe classe = student.getClasse();
				if (classe != null) {
					List<Teacher> teachers = new ArrayList<>(classe.getTeachers());

					for (int i = 0; i < teachers.size(); i++) {
						ListeCourses.addAll(courseService.getAllCoursesByTeacher(teachers.get(i)));
					}
					ListeCourses = courseService.getAllCourses();
				}
			} else if (user instanceof Teacher) {
				Teacher teacher = (Teacher) user;
				ListeCourses = courseService.getAllCoursesByTeacher(teacher);
			}
			pModel.addAttribute("listeCourses", ListeCourses);
			return "courses";
		} else {
			return "redirect:/login";
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
			return "courses/form";
		} else {
			return "ajax/error";
		}
	}

	@RequestMapping(value = { "/courses/new" }, method = RequestMethod.POST)
	public String saveCourse(@Valid Course course, BindingResult result, ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			if (result.hasErrors()) {
				return "ajax/error";
			}
			User user = userService.getUserByUsername(
					((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername());
			course.setTeacher((Teacher) user);
			course.setDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
			courseService.createCourse(course);
		} else {
			return "ajax/error";
		}
		return "ajax/success";
	}

	@RequestMapping(value = { "/courses/edit" }, method = RequestMethod.GET)
	public String editCourse(ModelMap model, @RequestParam(value = "id") int id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			Course course = courseService.getCourseById(id);
			if (course != null)
				model.addAttribute("course", course);
			else
				return "ajax/error";
			return "courses/form";
		} else {
			return "ajax/error";
		}
	}

	@RequestMapping(value = { "/courses/edit" }, method = RequestMethod.PUT)
	@ResponseBody
	public String modifyCourse(@Valid Course course, BindingResult result, ModelMap model,
			@RequestParam(value = "id", required = true) int id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			if (result.hasErrors()) {
				return "ERROR";
			}

			User user = userService.getUserByUsername(
					((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername());
			course.setTeacher((Teacher) user);
			course.setDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
			course.setIdCourse(id);
			courseService.updateCourse(course);
		} else {
			return "ERROR";
		}
		return "SUCCESS";
	}

	@RequestMapping(value = { "/courses/delete" }, method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteCourse(ModelMap model, @RequestParam(value = "id", required = true) int id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			courseService.deleteCourse(courseService.getCourseById(id));
			if (courseService.getCourseById(id) != null) {
				return "ERROR";
			}
		} else {
			return "ERROR";
		}
		return "SUCCESS";
	}

	@RequestMapping(value = { "/courses/seen/{id}" }, method = RequestMethod.PUT)
	@ResponseBody
	public String SeeCourse(@PathVariable(value = "id") int id, ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			User user = userService.getUserByUsername(
					((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername());
			// List<Course> courses = new ArrayList<>();
			// courses.addAll(courseService.getSeenCoursesByStudent((Student)
			// user));
			// Course course = courseService.getCourseById(id);
			if (!courseService.hasSeenCourse(courseService.getCourseById(id), (Student) user)) {
				SeenCourse seenCourse = new SeenCourse();
				seenCourse.setCourse(courseService.getCourseById(id));
				seenCourse.setStudent((Student) user);
				courseService.SeenCourse(seenCourse);
			}

		} else {
			return "ERROR";
		}
		return "SUCCESS";
	}
}
