package com.ensi.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class Course extends Document {
	private int idCourse;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "course_id", unique = true, nullable = false)
	public int getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(int idCourse) {
		this.idCourse = idCourse;
	}

}
