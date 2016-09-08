package com.ensi.project.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ensi.project.dao.CourseDao;
import com.ensi.project.model.Course;
import com.ensi.project.model.SeenCourse;
import com.ensi.project.model.Student;
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

	public SeenCourse Seen(SeenCourse seenCourse) {
		getSessionFactory().getCurrentSession().saveOrUpdate(seenCourse);
		return seenCourse;
	}

	public boolean hasSeen(Course course, Student student) {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SeenCourse.class);
		criteria.add(Restrictions.eq("student.id", student.getId()));
		criteria.add(Restrictions.eq("course.idCourse", course.getIdCourse()));
		return !criteria.list().isEmpty();
	}

	@SuppressWarnings("unchecked")
	public List<Course> findSeenCoursesByStudent(Student student) {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SeenCourse.class);
		criteria.add(Restrictions.eq("student.id", student.getId()));
		criteria.setProjection(Projections.property("course"));
		return criteria.list();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
