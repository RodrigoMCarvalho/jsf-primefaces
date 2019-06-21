package br.com.livraria.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.livraria.dao.UsuarioDAO;
import br.com.livraria.modelo.Usuario;

@Named
@ViewScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario = new Usuario();
	
	@Inject 
	private UsuarioDAO dao;
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public String efetuarLogin() {
		System.out.println("Efetando o login do usuário " + this.usuario.getEmail());
		FacesContext context = FacesContext.getCurrentInstance();
		
		Usuario UsuarioExiste = dao.existe(usuario);
		if (UsuarioExiste != null) {
			
			context.getExternalContext().getSessionMap().put("usuarioLogado", UsuarioExiste);
			return "livro?faces-redirect=true";
		} else {
		context.getExternalContext().getFlash().setKeepMessages(true);; //dura duas requisições devido ao "faces-redirect"
		context.addMessage(null, new FacesMessage("Usuário e/ou senha inválidos"));
		}
		return "login?faces-redirect=true";
	}
	
	public String deslogar() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login?faces-redirect=true";
	}
	
	public String formAutor() {
		return "autor?faces-redirect=true";
	}
	
	public String formLivro() {
		return "livro?faces-redirect=true";
	}
	
	public String formVendas() {
		return "vendas?faces-redirect=true";
	}
	
	public String formCarousel() {
		return "carousel?faces-redirect=true";
	}
	
	public String formRing() {
		return "ring?faces-redirect=true";
	}
	
	
	
	
	
}
