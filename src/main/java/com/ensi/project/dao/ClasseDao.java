package com.ensi.project.dao;

import java.util.List;

import com.ensi.project.model.Classe;

public interface ClasseDao {
	public Classe createClasse(Classe classe);

	public Classe findClasseById(Integer id);

	public Classe updateClasse(Classe classe);

	public void deleteClasse(Classe classe);

	public List<Classe> findAllClasses();
}
