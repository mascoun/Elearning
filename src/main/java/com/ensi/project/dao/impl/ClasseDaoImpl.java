package com.ensi.project.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import com.ensi.project.dao.ClasseDao;
import com.ensi.project.model.Classe;

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

	public void deleteClasse(Classe classe) {
		getSessionFactory().getCurrentSession().delete(classe);
	}

	@SuppressWarnings("unchecked")
	public List<Classe> findAllClasses() {
		System.out.println("DAO");
		List<Classe> listClasses = new ArrayList<Classe>();
		System.out.println(getSessionFactory().getCurrentSession().createQuery("from Classe"));
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
