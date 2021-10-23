package br.com.caelum.financas.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.TipoMovimentacao;

public class MovimentacaoDao {
	
	private EntityManager manager;

	public MovimentacaoDao(EntityManager manager) {
		this.manager = manager;
	}

	public Double mediapDaContaPeloTipo(Conta conta, TipoMovimentacao tipo) {
		TypedQuery<Double> query = manager.
				createQuery("select avg(m.valor) from Movimentacao m where m.conta=:pConta" 
															+ " and m.tipoMovimentacao = :pTipo", Double.class);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", tipo);
		
		double media = query.getSingleResult();
		return media;
	}

}
