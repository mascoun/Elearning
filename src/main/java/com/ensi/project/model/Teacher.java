package com.ensi.project.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users", catalog = "elearning")
@DiscriminatorValue("Teacher")
public class Teacher extends User {
	private String subject;
	private Set<Classe> classes;
	private Set<Course> courses;

	@Column(name = "subject")
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@ManyToMany(targetEntity = Classe.class, mappedBy = "teachers", fetch = FetchType.LAZY)
	public Set<Classe> getClasses() {
		return classes;
	}

	public void setClasses(Set<Classe> classe) {
		this.classes = classe;
	}

	@OneToMany(targetEntity = Course.class, mappedBy = "teacher", fetch = FetchType.LAZY)
	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

}
