package com.ensi.project.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ensi.project.dao.UserDao;
import com.ensi.project.model.Classe;
import com.ensi.project.model.Student;
import com.ensi.project.model.Teacher;
import com.ensi.project.model.UserRole;
import com.ensi.project.service.UserService;

public class UserServiceImpl implements UserService {
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

		com.ensi.project.model.User user = userDao.findByUserName(username);
		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());
		return buildUserForAuthentication(user, authorities);
	}

	public void save(com.ensi.project.model.User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		Set<UserRole> Roles = new HashSet<UserRole>();
		Roles.add(new UserRole(user, "ROLE_USER"));
		if (user instanceof Student)
			Roles.add(new UserRole(user, "ROLE_STUDENT"));
		else
			Roles.add(new UserRole(user, "ROLE_TEACHER"));
		// student.setUserRole(Roles);
		// UserRole userRole = new UserRole();
		// userRole.setUser(user);
		// userRole.setRole("ROLE_USER");
		userDao.save(user, Roles);
	}

	private User buildUserForAuthentication(com.ensi.project.model.User user, List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}

	public List<Student> getNoAffectedStudents() {
		return userDao.findNoAffectedStudents();
	}

	public List<Teacher> getAllTeachersNotIn(Classe classe) {
		return userDao.findAllTeachersNotIn(classe);
	}

	public void update(com.ensi.project.model.User user) {
		userDao.update(user);
	}

	public List<com.ensi.project.model.User> getAllUsers() {
		return userDao.findAllUsers();
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public BCryptPasswordEncoder getbCryptPasswordEncoder() {
		return bCryptPasswordEncoder;
	}

	public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public com.ensi.project.model.User getUserByUsername(String username) {
		return userDao.findByUserName(username);
	}

	public com.ensi.project.model.User getUserById(int id) {
		return userDao.findById(id);
	}

	public List<com.ensi.project.model.User> getNotEnabledUsers() {
		return userDao.findNotEnabledUsers();
	}

	public void delete(com.ensi.project.model.User user) {
		userDao.delete(user);
	}
}