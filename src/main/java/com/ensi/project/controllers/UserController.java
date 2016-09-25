package com.ensi.project.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ensi.project.model.Student;
import com.ensi.project.model.Teacher;
import com.ensi.project.model.User;
import com.ensi.project.service.UserService;

@Controller
public class UserController {

	// private static final Logger logger =
	// LoggerFactory.getLogger(FileUploadController.class);

	@Autowired
	UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}

	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}

		return error;
	}

	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		return "registration";
	}

	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, @RequestParam("passwordVerification") String VerifPassword,
			@RequestParam(value = "type") String type, @RequestParam(value = "subject") String subject,
			BindingResult result, ModelMap model) {
		Date fromFormat = null;
		try {
			fromFormat = new SimpleDateFormat("yyyy-MM-dd").parse(user.getBirthday());
		} catch (ParseException e) {
			model.addAttribute("error", "Date de naissance erroné.");
			return "registration";
		}
		String toFormat = new SimpleDateFormat("dd-MM-yyyy").format(fromFormat);
		try {
			InternetAddress emailAddr = new InternetAddress(user.getUsername());
			emailAddr.validate();
		} catch (AddressException ex) {
			model.addAttribute("error", "Email erroné.");
			return "registration";

		}
		user.setEnabled(false);
		user.setBirthday(toFormat);
		if (!user.getPassword().equals(VerifPassword)) {
			model.addAttribute("error", "Les deux mot de passe ne correspondent pas.");
			return "registration";
		}
		if (result.hasErrors()) {
			return "registration";
		}
		if (type.equals("Student")) {
			Student student = new Student();
			student.setUsername(user.getUsername());
			student.setBirthday(user.getBirthday());
			student.setPassword(user.getPassword());
			userService.save(student);
		} else if (type.equals("Teacher")) {
			Teacher teacher = new Teacher();
			teacher.setUsername(user.getUsername());
			teacher.setBirthday(user.getBirthday());
			teacher.setPassword(user.getPassword());
			teacher.setSubject(subject);
			userService.save(teacher);
		}
		model.addAttribute("msg", "Compte créé et doit etre vérifier par un administrateur.");
		return "redirect:login";
	}

}