package com.ensi.project.service;


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
import com.ensi.project.model.UserRole;

public class UserServiceImpl implements UserService {
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(final String username)
               throws UsernameNotFoundException {

		com.ensi.project.model.User user = userDao.findByUserName(username);
		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());
		return buildUserForAuthentication(user, authorities);
	}
	
	public void save(com.ensi.project.model.User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole("ROLE_USER");
		userDao.save(user,userRole);
	}

	private User buildUserForAuthentication(com.ensi.project.model.User user,
		List<GrantedAuthority> authorities) {
		return new User(user.getUsername(),
			user.getPassword(), user.isEnabled(),
                        true, true, true, authorities);
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


}