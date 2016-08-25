package com.ensi.project.service;

import java.util.List;

import com.ensi.project.model.Exercice;

public interface ExerciceService {
	public Exercice findExerciceById(Integer id);

	public Exercice updateExercice(Exercice exercice);

	public Exercice createExercice(Exercice exercice);

	public void deleteExercice(Exercice exercice);

	public List<Exercice> getAllExercices();
}
