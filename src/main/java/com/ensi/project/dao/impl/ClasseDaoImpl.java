package com.ensi.project.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.ensi.project.dao.ClasseDao;
import com.ensi.project.model.Classe;
import com.ensi.project.model.Student;

public class ClasseDaoImpl implements ClasseDao {

	private SessionFactory sessionFactory;

	public Classe createClasse(Classe classe) {
		Integer id = (Integer) getSessionFactory().getCurrentSession().save(classe);
		classe.setClasseId(id);
		return classe;
	}

	public Classe findClasseById(Integer id) {
		Classe classe = (Classe) getSessionFactory().getCurrentSession().get(Classe.class, id);
		return classe;
	}

	public Classe updateClasse(Classe classe) {
		getSessionFactory().getCurrentSession().merge(classe);
		return classe;
	}

	@Transactional
	public void deleteClasse(Classe classe) {
		Set<Student> students = new HashSet<>();
		students.addAll(classe.getStudents());
		getSessionFactory().getCurrentSession().delete(classe);
		for (Student s : students) {
			s.setClasse(null);
			getSessionFactory().getCurrentSession().merge(s);
		}
		// getSessionFactory().getCurrentSession().flush();
	}

	@SuppressWarnings("unchecked")
	public List<Classe> findAllClasses() {
		List<Classe> listClasses = new ArrayList<Classe>();
		listClasses = getSessionFactory().getCurrentSession().createQuery("from Classe").list();
		return listClasses;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
