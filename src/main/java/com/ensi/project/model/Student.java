package com.ensi.project.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users", catalog = "elearning")
@DiscriminatorValue("Student")
public class Student extends User {
	private Classe classe;

	@ManyToOne
	@JoinColumn(name = "classe_id")
	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}
}
