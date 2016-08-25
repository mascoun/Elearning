package com.ensi.project.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ensi.project.dao.CourseDao;
import com.ensi.project.model.Course;
import com.ensi.project.model.Teacher;

@Repository
public class CourseDaoImpl implements CourseDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Course createCourse(Course course) {
		Integer id = (Integer) getSessionFactory().getCurrentSession().save(course);
		course.setIdCourse(id);
		return course;
	};

	public Course findCourseById(Integer id) {
		Course course = (Course) getSessionFactory().getCurrentSession().get(Course.class, id);
		return course;
	}

	public Course updateCourse(Course course) {
		getSessionFactory().getCurrentSession().merge(course);
		return course;
	};

	public void deleteCourse(Course course) {
		getSessionFactory().getCurrentSession().delete(course);
	};

	@SuppressWarnings("unchecked")
	public List<Course> findAllCourses() {
		List<Course> listCourses = new ArrayList<Course>();
		listCourses = getSessionFactory().getCurrentSession().createQuery("from Course").list();
		return listCourses;
	}

	@SuppressWarnings("unchecked")
	public List<Course> findAllCoursesByTeacher(Teacher teacher) {
		List<Course> listCourses = new ArrayList<Course>();
		listCourses = getSessionFactory().getCurrentSession().createQuery("from Course where teacher=?")
				.setParameter(0, teacher).list();
		return listCourses;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
