package com.ensi.project.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import com.ensi.project.model.Message;
import com.ensi.project.model.User;
import com.ensi.project.service.MessageService;
import com.ensi.project.service.UserService;

@Controller
public class MessagingController {
	@Autowired
	UserService userService;

	@Autowired
	MessageService messageService;

	@RequestMapping(value = { "/messages" }, method = RequestMethod.GET)
	public String MessagesPage(final ModelMap pModel) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			User user = userService.getUserByUsername(
					((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername());
			Set<Message> ListeMessage = new HashSet<>();
			ListeMessage.addAll(messageService.getAllMessages(user));
			pModel.addAttribute("messages", ListeMessage);
			return "messages";
		} else {
			return "redirect:/login";
		}

	}

	@RequestMapping(value = { "/messages/new" }, method = RequestMethod.GET)
	public String NewMessage(final ModelMap pModel) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			User user = userService.getUserByUsername(
					((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername());
			Set<User> ListeUsers = new HashSet<>();
			ListeUsers.addAll(userService.getAllUsers());
			ListeUsers.remove((User) userService.getUserById(user.getId()));
			Message message = new Message();
			pModel.addAttribute("users", ListeUsers);
			pModel.addAttribute("message", message);
			return "form/message";
		} else {
			return "redirect:/login";
		}

	}

	@RequestMapping(value = { "/messages/new" }, method = RequestMethod.POST)
	@ResponseBody
	public String addMessage(@Valid Message message, BindingResult result,
			@RequestParam(value = "to", required = true) List<String> ids) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			User user = userService.getUserByUsername(
					((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername());
			List<Message> messages = new ArrayList<>();
			for (int i = 0; i < ids.size(); i++) {
				Message message_tmp = new Message();
				message_tmp.setObject(message.getObject());
				message_tmp.setBody(message.getBody());
				message_tmp.setFrom(user);
				message_tmp.setTo(userService.getUserById(Integer.parseInt(ids.get(i))));
				message_tmp.setDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
				message_tmp.setSeen(false);
				messages.add(message_tmp);
			}
			messageService.sendMessage(messages);
			return "SUCCESS";
		} else {
			return "ERROR";
		}

	}

	@RequestMapping(value = { "/messages/seen/{id}" }, method = RequestMethod.PUT)
	@ResponseBody
	public String SeenMessage(@PathVariable(value = "id") int id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			messageService.seenMessage(messageService.getMessageById(id));
			return "SUCCESS";
		} else {
			return "ERROR";
		}

	}

	@RequestMapping(value = { "/messages/{id}" }, method = RequestMethod.GET)
	public String viewMessage(@PathVariable(value = "id") int id, ModelMap pmodel) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			Message message = messageService.getMessageById(id);
			pmodel.addAttribute("message", message);
			return "message";
		} else {
			return "ajax/error";
		}

	}

}
