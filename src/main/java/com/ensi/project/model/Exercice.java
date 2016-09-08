package com.ensi.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exercices")
public class Exercice extends Document {
	private int idExercice;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "exercice_id", unique = true, nullable = false)
	public int getIdExercice() {
		return idExercice;
	}

	public void setIdExercice(int idExercice) {
		this.idExercice = idExercice;
	}

}
