package com.ensi.project.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ensi.project.dao.ExerciceDao;
import com.ensi.project.model.Exercice;
import com.ensi.project.model.SeenExercice;
import com.ensi.project.model.Student;
import com.ensi.project.model.Teacher;

@Repository
public class ExerciceDaoImpl implements ExerciceDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Exercice createExercice(Exercice exercice) {
		Integer id = (Integer) getSessionFactory().getCurrentSession().save(exercice);
		exercice.setIdExercice(id);
		return exercice;
	};

	public Exercice findExerciceById(Integer id) {
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
	public List<Exercice> findAllExercices() {
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

	@SuppressWarnings("unchecked")
	public List<Exercice> findSeenExercicesByStudent(Student student) {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SeenExercice.class);
		criteria.add(Restrictions.eq("student.id", student.getId()));
		criteria.setProjection(Projections.property("exercice"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Exercice> findAllExercicesByTeacher(Teacher teacher) {
		List<Exercice> listExercices = new ArrayList<Exercice>();
		listExercices = getSessionFactory().getCurrentSession().createQuery("from Exercice where teacher=?")
				.setParameter(0, teacher).list();
		return listExercices;
	}

	public boolean hasSeen(Exercice exercice, Student student) {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SeenExercice.class);
		criteria.add(Restrictions.eq("student.id", student.getId()));
		criteria.add(Restrictions.eq("exercice.idExercice", exercice.getIdExercice()));
		return !criteria.list().isEmpty();
	}

	public SeenExercice Seen(SeenExercice seenExercice) {
		getSessionFactory().getCurrentSession().saveOrUpdate(seenExercice);
		return seenExercice;
	}

}
