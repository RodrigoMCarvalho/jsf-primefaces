package br.com.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.livraria.dao.AutorDAO;
import br.com.livraria.dao.LivroDAO;
import br.com.livraria.modelo.Autor;
import br.com.livraria.modelo.Livro;

@Named
@ViewScoped //a cada request � gerado um novo livroBean, viewScoped evita isso
public class LivroBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Livro livro = new Livro();
	private Integer autorId;
	private List<Livro> livros;
	
	@Inject
	private AutorDAO autorDao;
	
	@Inject
	private LivroDAO livroDao;
	
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
		return autorDao.listaTodos();
	}
	
	public List<Livro> getLivros() {
		if(this.livros == null) { //para quando for filtrar os dados na tabela, realizar apenas um consulta no BD
			this.livros = livroDao.listaTodos();
		}
		return livros;
	}
	
	public void gravarAutor() {
		FacesContext context = FacesContext.getCurrentInstance();
		Autor autor = autorDao.buscaPorId(autorId);
		livro.adicionaAutor(autor);
		context.addMessage(null, new FacesMessage("Autor associado com sucesso."));
	}

	public void gravar() {
		//System.out.println("Gravando livro " + this.livro.getTitulo());
		
		FacesContext context = FacesContext.getCurrentInstance();

		if (livro.getAutores().isEmpty()) {
			//throw new RuntimeException("Livro deve ter pelo menos um Autor.");
			context.addMessage(null, new FacesMessage("Livro deve ter pelo menos um Autor."));
			return;
		}
		if (this.livro.getId() == null) {
			livroDao.adiciona(this.livro);
			this.livros = livroDao.listaTodos();  //ap�s persister no banco, atualiza a tabela de livros
			context.addMessage(null, new FacesMessage("Livro gravado com sucesso!"));
		} else {
			livroDao.atualiza(this.livro);
		}
		livro = new Livro();
	}
	
	public void remover(Livro livro) {
		livroDao.remove(livro);
		this.livro = new Livro();
		this.livros = livroDao.listaTodos(); //ap�s remover no banco, atualiza a tabela de livros
	}
	
	public void removerAutorDoLivro(Autor autor) {
		this.livro.removerAutor(autor);
	}
	
	public void alterar(Livro livro) {
		this.livro = livro; //livro atributo recebe o par�metro livro
	}
	
	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object obj)throws ValidatorException {
		String valor = obj.toString();
		if(!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("ISBN dever� come�ar com 1"));
		}
	}
	
}
