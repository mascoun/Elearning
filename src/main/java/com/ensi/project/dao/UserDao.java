package com.ensi.project.dao;

import com.ensi.project.model.User;
import com.ensi.project.model.UserRole;

public interface UserDao {
	User findByUserName(String username);
	void save(User user, UserRole userRole);
	void delete(User user);
}