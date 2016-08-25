package com.ensi.project.dao;

import java.util.List;

import com.ensi.project.model.Exercice;

public interface ExerciceDao {
	public Exercice createExercice(Exercice exercice);

	public Exercice getExerciceById(Integer id);

	public Exercice updateExercice(Exercice exercice);

	public void deleteExercice(Exercice exercice);

	public List<Exercice> getAllExercices();
}