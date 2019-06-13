package br.com.livraria.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.livraria.dao.DAO;
import br.com.livraria.modelo.Autor;
import br.com.livraria.modelo.Livro;

@ManagedBean
@ViewScoped //a cada request é gerado um novo livroBean, viewScoped evita isso
public class LivroBean {

	private Livro livro = new Livro();
	private Integer autorId;
	private List<Livro> livros;
	
	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	public Integer getAutorId() {
		return autorId;
	}
	
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	public Livro getLivro() {
		return livro;
	}
	
	public List<Autor> getAutoresDoLivro() {
		return livro.getAutores();
	}
	
	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}
	
	public List<Livro> getLivros() {
		if(this.livros == null) { //para quando for filtrar os dados na tabela, realizar apenas um consulta no BD
			this.livros = new DAO<Livro>(Livro.class).listaTodos();
		}
		return livros;
	}
	
	public void gravarAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
		livro.adicionaAutor(autor);
	}

	public void gravar() {
		//System.out.println("Gravando livro " + this.livro.getTitulo());
		
		FacesContext context = FacesContext.getCurrentInstance();
		DAO<Livro> dao = new DAO<Livro>(Livro.class);

		if (livro.getAutores().isEmpty()) {
			//throw new RuntimeException("Livro deve ter pelo menos um Autor.");
			context.addMessage(null, new FacesMessage("Livro deve ter pelo menos um Autor."));
			return;
		}
		if (this.livro.getId() == null) {
			dao.adiciona(this.livro);
			this.livros = dao.listaTodos();  //após persister no banco, atualiza a tabela de livros
			context.addMessage(null, new FacesMessage("Livro gravado com sucesso!"));
		} else {
			dao.atualiza(this.livro);
		}
		livro = new Livro();
	}
	
	public void remover(Livro livro) {
		DAO<Livro> dao = new DAO<Livro>(Livro.class);
		dao.remove(livro);
		this.livros = dao.listaTodos(); //após remover no banco, atualiza a tabela de livros
	}
	
	public void removerAutorDoLivro(Autor autor) {
		this.livro.removerAutor(autor);
	}
	
	public void alterar(Livro livro) {
		this.livro = livro; //livro atributo recebe o parâmetro livro
	}
	
	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object obj)throws ValidatorException {
		String valor = obj.toString();
		if(!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("ISBN deverá começar com 1"));
		}
	}
	
}
