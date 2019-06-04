package br.com.livraria.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.livraria.modelo.Usuario;

public class UsuarioDAO {

	public Usuario existe(Usuario usuario) {
		
		Usuario resultado = new Usuario();
		EntityManager em = new JPAUtil().getEntityManager();
		TypedQuery<Usuario> query = em.createQuery("select u from Usuario u "
				+ "	where u.email = :pEmail and u.senha = :pSenha", Usuario.class);
		query.setParameter("pEmail", usuario.getEmail());
		query.setParameter("pSenha", usuario.getSenha());
		
		try {
			resultado = query.getSingleResult();
		} catch (NoResultException erro) {
			erro.printStackTrace();
			return null;
		}
		em.close();

		return resultado;  
	}

}
