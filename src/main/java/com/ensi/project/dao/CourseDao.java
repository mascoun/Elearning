package com.ensi.project.dao;

import java.util.List;

import com.ensi.project.model.Course;
import com.ensi.project.model.SeenCourse;
import com.ensi.project.model.Student;
import com.ensi.project.model.Teacher;

public interface CourseDao {
	public Course createCourse(Course course);

	public Course findCourseById(Integer id);

	public Course updateCourse(Course course);

	public void deleteCourse(Course course);

	public List<Course> findAllCourses();

	public List<Course> findSeenCoursesByStudent(Student student);

	public List<Course> findAllCoursesByTeacher(Teacher teacher);

	public boolean hasSeen(Course course, Student student);

	public SeenCourse Seen(SeenCourse seenCourse);
}