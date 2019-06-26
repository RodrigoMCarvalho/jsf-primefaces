package br.com.livraria.dao;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.livraria.modelo.Usuario;

@Named
@ViewScoped
public class UsuarioDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager em;

	public Usuario existe(Usuario usuario) {

		Usuario resultado = new Usuario();
		TypedQuery<Usuario> query = em.createQuery(
				"select u from Usuario u " + "	where u.email = :pEmail and u.senha = :pSenha", Usuario.class);
		query.setParameter("pEmail", usuario.getEmail());
		query.setParameter("pSenha", usuario.getSenha());

		try {
			resultado = query.getSingleResult();
		} catch (NoResultException erro) {
			erro.printStackTrace();
			return null;
		}

		return resultado;
	}

}
