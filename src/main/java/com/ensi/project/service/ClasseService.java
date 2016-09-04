package com.ensi.project.service;

import java.util.List;

import com.ensi.project.model.Classe;

public interface ClasseService {
	public Classe getClasseById(Integer id);

	public Classe updateClasse(Classe classe);

	public Classe createClasse(Classe classe);

	public void deleteClasse(Classe classe);

	public List<Classe> getAllClasses();

}
