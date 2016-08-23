package com.ensi.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "courses", catalog = "elearning")
public class Course {
	private int idCourse;
	private String name;
	private String description;
	private Teacher teacher;
	private String date;
	private String link;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "course_id", unique = true, nullable = false, insertable = false, updatable = false)
	public int getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(int idCourse) {
		this.idCourse = idCourse;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne
	@JoinColumn(name = "id", nullable = false)
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@Column(name = "date", nullable = false)
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Column(name = "link", nullable = false)
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
