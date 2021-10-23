package br.com.caelum.financas.teste;

import java.util.List;

import javax.management.Query;
import javax.persistence.EntityManager;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteConsulta {
	

	public static void main(String[] args) {
		
		EntityManager manager = new JPAUtil().getEntityManager();
		Conta conta = new Conta();
		conta.setId(3);
		
		
		//CONSULTA SIMPLE CONCATENADA
		//Query query = manager
        //.createQuery("select m from Movimentacao m where m.conta.id="
        //        + conta.getId());
		
		//POSITIONAL PARAMETERS NOTATION
		//javax.persistence.Query query = manager.createQuery("select m from Movimentacao m where m.conta=?1");
		//query.setParameter(1, conta);
		
		//NAMED PARAMETERS NOTATION
		javax.persistence.Query query = manager.createQuery("select m from Movimentacao m where m.conta=:pConta" 
															+ " and m.tipoMovimentacao = :pTipo" 
															+ " order by m.valor desc");
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);
		
		List<Movimentacao> movimentacoes =  query.getResultList();
		
		for(Movimentacao m : movimentacoes){
			System.out.println("\nDescrição ..........: " + m.getDescricao());
			System.out.println("Valor     ..........: R$ " + m.getValor());
		}
	}

}
