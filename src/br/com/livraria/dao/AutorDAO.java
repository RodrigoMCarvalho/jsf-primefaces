package br.com.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.livraria.modelo.Autor;

public class AutorDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;
	
	private DAO<Autor> dao;
	
	@PostConstruct
	void init() {
		this.dao = new DAO<Autor>(this.em, Autor.class);
	}

	public void adiciona(Autor autor) {
		dao.adiciona(autor);
	}

	public void remove(Autor autor) {
		dao.remove(autor);
	}

	public void atualiza(Autor autor) {
		dao.atualiza(autor);
	}

	public Autor buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}

	public List<Autor> listaTodos() {
		return dao.listaTodos();
	}

	

}
