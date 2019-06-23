package br.com.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.livraria.dao.AutorDAO;
import br.com.livraria.modelo.Autor;
import br.com.livraria.tx.Transacional;

@Named
@ViewScoped
public class AutorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Autor autor = new Autor();
	
	@Inject
	private AutorDAO dao; //CDI faz new AutorDAO() e injeta

		
	private Integer autorId;

	public Autor getAutor() {
		return autor;
	}
	
	public List<Autor> getAutores() {
		return dao.listaTodos();
	} 

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	@Transacional
	public void gravar() {
		System.out.println("Gravando autor " + this.autor.getNome() + " ID: " + this.autor.getId());
		
		if(autor.getId() == null) {
			this.dao.adiciona(this.autor);
		} else {
			this.dao.atualiza(this.autor);
		}
		autor = new Autor(); //para limpar após persistir um novo autor
	}
	
	@Transacional
	public void remover(Autor autor) {
		this.dao.remove(autor);
		this.autor = new Autor(); //caso carregue o usuário antes de excluir, irá limpar os campos
	}
	
	public void carregar(Autor autor) {
		this.autor = autor;
		System.out.println(autor.getId() + "Nome: " + autor.getNome());
	}
	
	public void carregarAutorPorId () {
		this.autor = this.dao.buscaPorId(autorId);
	}
	
	
	
	
	
	
	
}
