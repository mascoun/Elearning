package com.ensi.project.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ensi.project.dao.ExerciceDao;
import com.ensi.project.model.Exercice;

@Repository
public class ExerciceDaoImpl implements ExerciceDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Exercice createExercice(Exercice exercice) {
		Integer id = (Integer) getSessionFactory().getCurrentSession().save(exercice);
		exercice.setIdExercice(id);
		return exercice;
	};

	public Exercice getExerciceById(Integer id) {
		Exercice exercice = (Exercice) getSessionFactory().getCurrentSession().get(Exercice.class, id);
		return exercice;
	}

	public Exercice updateExercice(Exercice exercice) {
		getSessionFactory().getCurrentSession().merge(exercice);
		return exercice;
	};

	public void deleteExercice(Exercice exercice) {
		getSessionFactory().getCurrentSession().delete(exercice);
	};

	@SuppressWarnings("unchecked")
	public List<Exercice> getAllExercices() {
		List<Exercice> listExercice = new ArrayList<Exercice>();
		listExercice = getSessionFactory().getCurrentSession().createQuery("from Exercice").list();
		return listExercice;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
