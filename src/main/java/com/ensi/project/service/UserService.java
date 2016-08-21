package com.ensi.project.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ensi.project.model.User;

public interface UserService extends UserDetailsService{
	public void save(User user);
}
