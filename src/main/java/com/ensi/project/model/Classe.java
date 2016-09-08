package com.ensi.project.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "classe")
public class Classe {
	private int classeId;
	private String name;

	private Set<Student> students;
	private Set<Teacher> teachers;

	@OneToMany(targetEntity = Student.class, mappedBy = "classe", fetch = FetchType.EAGER)
	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	@ManyToMany(targetEntity = Teacher.class, cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	@JoinTable(name = "classe_teacher", joinColumns = @JoinColumn(name = "classe_id") , inverseJoinColumns = @JoinColumn(name = "id") )
	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "classe_id", insertable = false, updatable = false)
	public int getClasseId() {
		return classeId;
	}

	public void setClasseId(int classeId) {
		this.classeId = classeId;
	}

}
