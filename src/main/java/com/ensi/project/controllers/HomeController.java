package com.ensi.project.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	@RequestMapping(value = { "/", "/home**" }, method = RequestMethod.GET)
	public String homePage(HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			if (request.isUserInRole("ROLE_ADMIN")) {
				return "redirect:/admin";
			} else {
				return "home";
			}
		} else {
			return "redirect:login";
		}

	}
}
