package com.ensi.project.service.impl;

import java.util.List;

import com.ensi.project.dao.ExerciceDao;
import com.ensi.project.model.Exercice;
import com.ensi.project.service.ExerciceService;

public class ExerciceServiceImpl implements ExerciceService {

	private ExerciceDao exerciceDao;

	public Exercice findExerciceById(Integer id) {
		Exercice exercice = exerciceDao.getExerciceById(id);
		return exercice;
	}

	public Exercice updateExercice(Exercice exercice) {
		exercice = exerciceDao.updateExercice(exercice);
		return exercice;
	}

	public Exercice createExercice(Exercice exercice) {
		exercice = exerciceDao.createExercice(exercice);
		return exercice;
	}

	public List<Exercice> getAllExercices() {
		List<Exercice> listExercices = exerciceDao.getAllExercices();
		return listExercices;
	}

	public void deleteExercice(Exercice exercice) {
		exerciceDao.deleteExercice(exercice);
	}

	public ExerciceDao getExerciceDao() {
		return exerciceDao;
	}

	public void setExerciceDao(ExerciceDao exerciceDao) {
		this.exerciceDao = exerciceDao;
	}
}
