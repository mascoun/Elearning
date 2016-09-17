package com.ensi.project.service.impl;

import java.util.List;

import com.ensi.project.dao.ExerciceDao;
import com.ensi.project.model.Exercice;
import com.ensi.project.model.Student;
import com.ensi.project.model.Teacher;
import com.ensi.project.service.ExerciceService;

public class ExerciceServiceImpl implements ExerciceService {

	private ExerciceDao exerciceDao;

	public Exercice getExerciceById(Integer id) {
		Exercice exercice = exerciceDao.findExerciceById(id);
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
		List<Exercice> listExercices = exerciceDao.findAllExercices();
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

	public List<Exercice> getSeenExercicesByStudent(Student student) {
		return exerciceDao.findSeenExercicesByStudent(student);
	}

	public List<Exercice> getAllExercicesByTeacher(Teacher teacher) {
		return exerciceDao.findAllExercicesByTeacher(teacher);
	}

	public void SeenExercice(com.ensi.project.model.SeenExercice seenExercice) {
		 exerciceDao.Seen(seenExercice);
	}

	public boolean hasSeenExercice(Exercice exercice, Student student) {
		return exerciceDao.hasSeen(exercice, student);
	}
}
