package br.com.livraria.tx;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Transacional
@Interceptor   //executar uma ação antes e depois
public class GerenciadorDeTransacao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	EntityManager em;
	
	@AroundInvoke
	public Object executaTx(InvocationContext context) throws Exception {
		System.out.println("Abrindo transação");
		// abre transacao
		em.getTransaction().begin();
		
		//chamar os DAOs que precisam  de uma Tx
		Object resultado = context.proceed();

		// commita a transacao
		em.getTransaction().commit();
		System.out.println("Fechando transação");
		return resultado;
	}
}
