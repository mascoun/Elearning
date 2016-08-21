package com.ensi.project.service;

import java.util.List;

import com.ensi.project.model.Course;

public interface CourseService {
	public Course findCourseById(Integer id);

	public Course updateCourse(Course course);

	public Course createCourse(Course course);

	public void deleteCourse(Course course);

	public List<Course> getAllCourses();
}
