package com.ensi.project.dao;

import java.util.List;

import com.ensi.project.model.Course;

public interface CourseDao {
	public Course createCourse(Course course);

	public Course findCourseById(Integer id);

	public Course updateCourse(Course course);

	public void deleteCourse(Course course);

	public List<Course> getAllCourses();
}