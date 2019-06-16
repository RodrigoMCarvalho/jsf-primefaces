package br.com.livraria.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.livraria.dao.UsuarioDAO;
import br.com.livraria.modelo.Usuario;

@ManagedBean
@ViewScoped
public class LoginBean {

	private Usuario usuario = new Usuario();
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public String efetuarLogin() {
		System.out.println("Efetando o login do usu�rio " + this.usuario.getEmail());
		FacesContext context = FacesContext.getCurrentInstance();
		
		Usuario UsuarioExiste = new UsuarioDAO().existe(usuario);
		if (UsuarioExiste != null) {
			
			context.getExternalContext().getSessionMap().put("usuarioLogado", UsuarioExiste);
			return "livro?faces-redirect=true";
		} else {
		context.getExternalContext().getFlash().setKeepMessages(true);; //dura duas requisi��es devido ao "faces-redirect"
		context.addMessage(null, new FacesMessage("Usu�rio e/ou senha inv�lidos"));
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
