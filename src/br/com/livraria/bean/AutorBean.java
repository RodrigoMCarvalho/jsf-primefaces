package br.com.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.livraria.dao.DAO;
import br.com.livraria.modelo.Autor;

@Named
@ViewScoped
public class AutorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Autor autor = new Autor();
	
	private Integer autorId;

	public Autor getAutor() {
		return autor;
	}
	
	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	} 

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public void gravar() {
		System.out.println("Gravando autor " + this.autor.getNome() + " ID: " + this.autor.getId());
		
		if(autor.getId() == null) {
			new DAO<Autor>(Autor.class).adiciona(this.autor);
		} else {
			new DAO<Autor>(Autor.class).atualiza(this.autor);
		}
		autor = new Autor(); //para limpar após persistir um novo autor
	}
	
	public void remover(Autor autor) {
		new DAO<Autor>(Autor.class).remove(autor);
		this.autor = new Autor(); //caso carregue o usuário antes de excluir, irá limpar os campos
	}
	
	public void carregar(Autor autor) {
		this.autor = autor;
		System.out.println(autor.getId() + "Nome: " + autor.getNome());
	}
	
	public void carregarAutorPorId () {
		this.autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
	}
	
	
	
	
	
	
	
}
