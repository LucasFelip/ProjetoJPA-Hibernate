package br.com.caelum.financas.teste;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Scanner;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteJPA {

  public static void main(String[] args) {
	
		Conta conta = new Conta();
		Movimentacao mov = new Movimentacao();
		Scanner keyboard = new Scanner(System.in);
		EntityManager manager = new JPAUtil().getEntityManager();
		
		int i = 0, tipo;
		String nome, banco, agencia, numero, descricao;
		BigDecimal valor;
		
		do {
			System.out.println("---------- FINANCAS ---------- ");
			System.out.println(" 1 - Inserir conta ");
			System.out.println(" 2 - Inserir movimenta��o ");
			System.out.println(" 3 - Realizar consulta ");
			System.out.println(" 0 - Sair");
			System.out.println("------------------------------ ");
			System.out.print(" Coloque uma op��o v�lida: ");
			i = keyboard.nextInt();
			
			System.out.println();
			switch(i) {
				case 0:
					System.out.println("Saindo!");
					break;
				case 1:
					System.out.print("Nome: "); nome = keyboard.next();
					System.out.print("Banco: "); banco = keyboard.next();
					System.out.print("Ag�ncia: "); agencia = keyboard.next();
					System.out.print("N�mero: "); numero = keyboard.next();
					
					conta.setTitular(nome);
					conta.setBanco(banco);
					conta.setNumero(numero);
					conta.setAgencia(agencia);
					
					manager.getTransaction().begin();
					
					manager.persist(conta);
					
					manager.getTransaction().commit();
					manager.close();
					break;
					
				case 2:
					System.out.print("Descri��o: "); descricao = keyboard.next();
					System.out.print("Tipo de Movimenta��o (0 - Entrada/ 1 - Sa�da): "); tipo = keyboard.nextInt();
					System.out.print("Valor: "); valor = keyboard.nextBigDecimal();
					System.out.print("ID: "); numero = keyboard.next();
					
					mov.setData(Calendar.getInstance());
					mov.setDescricao(descricao);
					if(tipo == 0)
						mov.setTipoMovimentacao(TipoMovimentacao.ENTRADA);
					else
						mov.setTipoMovimentacao(TipoMovimentacao.SAIDA);
					mov.setValor(valor);
					
					conta.setNumero(numero);
					mov.setConta(conta);
					break;
				
				case 3:
					
					break;
					
				default:
					System.out.print(" Coloque uma op��o v�lida!");
					break;
			}
			
		}while(i != 0);
		
		
		
		
		
		mov.setConta(conta);
		
		
		

	}
}
 