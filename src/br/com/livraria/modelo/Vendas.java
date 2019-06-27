package br.com.livraria.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Vendas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Livro livro_id;
	private Integer quantidade;

	public Vendas(Livro livro, Integer quantidade) {
		this.livro_id = livro;
		this.quantidade = quantidade;
	}

	public Vendas() {
	}

	public Livro getLivro() {
		return livro_id;
	}

	public void setLivro(Livro livro) {
		this.livro_id = livro;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
