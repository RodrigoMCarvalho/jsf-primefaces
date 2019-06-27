package br.com.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.livraria.modelo.Vendas;

public class VendasDAO implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;
	
	private DAO<Vendas> dao;
	
	@PostConstruct
	void init() {
		this.dao = new DAO<Vendas>(this.em, Vendas.class);
	}
	
	public List<Vendas> buscaVendas() {
		return dao.listaTodos();
	}
 }
