package com.ensi.project.dao;

import java.util.List;

import com.ensi.project.model.Exercice;
import com.ensi.project.model.SeenExercice;
import com.ensi.project.model.Student;
import com.ensi.project.model.Teacher;

public interface ExerciceDao {
	public Exercice createExercice(Exercice exercice);

	public Exercice findExerciceById(Integer id);

	public Exercice updateExercice(Exercice exercice);

	public void deleteExercice(Exercice exercice);

	public List<Exercice> findAllExercices();

	public List<Exercice> findSeenExercicesByStudent(Student student);

	public List<Exercice> findAllExercicesByTeacher(Teacher teacher);

	public boolean hasSeen(Exercice exercice, Student student);

	public SeenExercice Seen(SeenExercice seenExercice);
}