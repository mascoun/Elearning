package com.ensi.project.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ensi.project.model.Classe;
import com.ensi.project.model.Student;
import com.ensi.project.model.Teacher;
import com.ensi.project.model.User;

public interface UserService extends UserDetailsService {
	public void save(User user);

	public void update(User user);

	public User getUserByUsername(String username);

	public User getUserById(int id);

	public List<Student> getNoAffectedStudents();

	public List<Teacher> getAllTeachersNotIn(Classe classe);
}
