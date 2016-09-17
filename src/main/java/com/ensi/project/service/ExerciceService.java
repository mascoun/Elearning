package com.ensi.project.service;

import java.util.List;

import com.ensi.project.model.Exercice;
import com.ensi.project.model.SeenExercice;
import com.ensi.project.model.Student;
import com.ensi.project.model.Teacher;

public interface ExerciceService {
	public Exercice getExerciceById(Integer id);

	public Exercice updateExercice(Exercice exercice);

	public Exercice createExercice(Exercice exercice);

	public void deleteExercice(Exercice exercice);

	public List<Exercice> getAllExercices();

	public List<Exercice> getSeenExercicesByStudent(Student student);

	public List<Exercice> getAllExercicesByTeacher(Teacher teacher);

	public void SeenExercice(SeenExercice seenExercice);

	public boolean hasSeenExercice(Exercice exercice, Student student);
}
