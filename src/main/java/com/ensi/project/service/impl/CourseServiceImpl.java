package com.ensi.project.service.impl;

import java.util.List;

import com.ensi.project.dao.CourseDao;
import com.ensi.project.model.Course;
import com.ensi.project.model.Teacher;
import com.ensi.project.service.CourseService;

public class CourseServiceImpl implements CourseService {

	private CourseDao courseDao;

	public Course getCourseById(Integer id) {
		Course course = courseDao.findCourseById(id);
		return course;
	}

	public Course updateCourse(Course course) {
		course = courseDao.updateCourse(course);
		return course;
	}

	public Course createCourse(Course course) {
		course = courseDao.createCourse(course);
		return course;
	}

	public List<Course> getAllCourses() {
		List<Course> listCourses = courseDao.findAllCourses();
		return listCourses;
	}

	public void deleteCourse(Course course) {
		courseDao.deleteCourse(course);
	}

	public CourseDao getCourseDao() {
		return courseDao;
	}

	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	public List<Course> getAllCoursesByTeacher(Teacher teacher) {
		return courseDao.findAllCoursesByTeacher(teacher);
	}
}
