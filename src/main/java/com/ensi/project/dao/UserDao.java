package com.ensi.project.dao;

import java.util.List;

import com.ensi.project.model.Classe;
import com.ensi.project.model.Student;
import com.ensi.project.model.Teacher;
import com.ensi.project.model.User;
import com.ensi.project.model.UserRole;

public interface UserDao {
	User findByUserName(String username);

	User findById(int id);

	void save(User user, UserRole userRole);

	void delete(User user);

	List<Student> findNoAffectedStudents();

	List<Teacher> findAllTeachersNotIn(Classe classe);

	User update(User user);

	List<User> findAllUsers();
}