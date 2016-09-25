package com.ensi.project.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.ensi.project.dao.UserDao;
import com.ensi.project.model.Classe;
import com.ensi.project.model.Student;
import com.ensi.project.model.Teacher;
import com.ensi.project.model.User;
import com.ensi.project.model.UserRole;

public class UserDaoImpl implements UserDao {

	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public User findByUserName(String username) {

		List<User> users = new ArrayList<User>();

		users = getSessionFactory().getCurrentSession().createQuery("from User where username=?")
				.setParameter(0, username).list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}

	@Transactional
	public void save(User user, Set<UserRole> userRoles) {
		getSessionFactory().getCurrentSession().persist(user);
		for (UserRole userRole : userRoles) {
			getSessionFactory().getCurrentSession().persist(userRole);
		}
	}

	public void delete(User user) {
		getSessionFactory().getCurrentSession().delete(user);
	}

	@SuppressWarnings("unchecked")
	public List<Student> findNoAffectedStudents() {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Student.class);
		criteria.add(Restrictions.isNull("classe"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Teacher> findAllTeachersNotIn(Classe classe) {
		Criteria criteria1 = getSessionFactory().getCurrentSession().createCriteria(Teacher.class);
		criteria1.createAlias("classes", "classe");
		criteria1.add(Restrictions.ne("classe.classeId", classe.getClasseId()));

		Criteria criteria2 = getSessionFactory().getCurrentSession().createCriteria(Teacher.class);
		criteria2.add(Restrictions.isEmpty("classes"));

		Set<Teacher> result = new HashSet<>();
		result.addAll(criteria1.list());
		result.addAll(criteria2.list());
		return new ArrayList<>(result);

	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public User findById(int id) {

		List<User> users = new ArrayList<User>();

		users = getSessionFactory().getCurrentSession().createQuery("from User where id=?").setParameter(0, id).list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}

	public User update(User user) {
		getSessionFactory().getCurrentSession().merge(user);
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		List<User> users = new ArrayList<User>();
		users.addAll(getSessionFactory().getCurrentSession().createCriteria(User.class).list());
		return users;
	}

	@SuppressWarnings("unchecked")
	public List<User> findNotEnabledUsers() {
		List<User> users = new ArrayList<User>();
		users = getSessionFactory().getCurrentSession().createQuery("from User where enabled=?").setParameter(0, false)
				.list();
		return users;
	}
}