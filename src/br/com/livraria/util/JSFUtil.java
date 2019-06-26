package br.com.livraria.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

public class JSFUtil {
	
	@Produces     //como produzir
	@RequestScoped  //uma vez por requisição
	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
}
