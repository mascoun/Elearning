package com.ensi.project.dao;

import com.ensi.project.model.User;

public interface UserDao {
	User findByUserName(String username);
}