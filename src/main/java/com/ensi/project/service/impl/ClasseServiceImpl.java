package com.ensi.project.service.impl;

import java.util.List;

import com.ensi.project.dao.ClasseDao;
import com.ensi.project.model.Classe;
import com.ensi.project.service.ClasseService;

public class ClasseServiceImpl implements ClasseService {

	private ClasseDao classeDao;

	public Classe getClasseById(Integer id) {
		return classeDao.findClasseById(id);
	}

	public Classe updateClasse(Classe classe) {
		return classeDao.updateClasse(classe);
	}

	public Classe createClasse(Classe classe) {
		return classeDao.createClasse(classe);
	}

	public void deleteClasse(Classe classe) {
		classeDao.deleteClasse(classe);

	}

	public List<Classe> getAllClasses() {
		return classeDao.findAllClasses();
	}

	public ClasseDao getClasseDao() {
		return classeDao;
	}

	public void setClasseDao(ClasseDao classeDao) {
		this.classeDao = classeDao;
	}

}
