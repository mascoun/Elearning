package com.ensi.project.service;

import java.util.List;

import com.ensi.project.model.Course;
import com.ensi.project.model.Teacher;

public interface CourseService {
	public Course getCourseById(Integer id);

	public Course updateCourse(Course course);

	public Course createCourse(Course course);

	public void deleteCourse(Course course);

	public List<Course> getAllCourses();

	public List<Course> getAllCoursesByTeacher(Teacher teacher);
}
