package br.com.cursojava.studentsystem.sistema.backend.aluno.dao.teste;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import br.com.cursojava.studentsystem.connection.HibernateConnection;
import br.com.cursojava.studentsystem.exception.ApplicationException;
import br.com.cursojava.studentsystem.sistema.backend.aluno.dao.AlunoDAO;
import br.com.cursojava.studentsystem.sistema.backend.aluno.model.AlunoPO;
import br.com.cursojava.studentsystem.sistema.backend.endereco.model.EnderecoPO;
import br.com.cursojava.studentsystem.utilidades.Utilidades;

public class TestaDAO{
	@Test
	public void executar() {

		AlunoDAO alunoDAO = new AlunoDAO();

		HibernateConnection hibernate = new HibernateConnection();

		try {

			/*O básico para o uso do COMPONENTE HibernateConnection é:
			 * -Iniciar transação;
			 * -Fazer o que tem que fazer;
			 * -Confirmar a transação, caso de certo!;
			 * -Desfazer a transação, caso de errado!;*/

			//			-INICIAR TRANSAÇÃO;
			hibernate.iniciarTransacao();

			//			-FAZER O QUE TEM QUE FAZER;
			List< AlunoPO > encontrados = alunoDAO.filtrarTodos();
			System.out.println( encontrados );

			AlunoPO aluno = new AlunoPO();
			aluno.setNome( "André Duarte" );
			aluno.setSexo( "Masculino" );
			aluno.setCpf( "387.739.318-70" );
			aluno.setDataNascimento( Utilidades.parseDate( "18/11/1989" ) );

			EnderecoPO endereco = new EnderecoPO();
			endereco.setLogradouro( "Rua Mauricio Biondo Neto" );
			endereco.setNumero( "674" );
			endereco.setBairro( "COHAB" );
			endereco.setCep( "19905-280" );
			endereco.setCidade( "Ourinhos" );
			endereco.setUf( "SP" );

			aluno.setEndereco( endereco );

			aluno.setRa( "12345678" );
			aluno.setPeso( new BigDecimal( "65.0" ) );
			aluno.setAltura( new BigDecimal( "1.70" ) );
			aluno.setTurma( "Turma XXXI Noturna de Seg a Sex" );

			alunoDAO.inserir( aluno, hibernate );

			encontrados = alunoDAO.filtrarTodos();
			System.out.println( encontrados );

			AlunoPO encontradoPorId = alunoDAO.filtrarPorId( aluno.getId() );

			encontradoPorId.setTurma( "Sem turma" );
			alunoDAO.alterar( encontradoPorId, hibernate );

			encontrados = alunoDAO.filtrarTodos();
			System.out.println( encontrados );

			encontradoPorId = alunoDAO.filtrarPorId( aluno.getId() );
			System.out.println( encontradoPorId );

			//alunoDAO.excluir( encontradoPorId );

			encontrados = alunoDAO.filtrarTodos();
			System.out.println( encontrados );

			//			-CONFIRMAR A TRANSAÇÃO, CASO DE CERTO!;
			hibernate.commitTransacao();

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			//			-DESFAZER A TRANSAÇÃO, CASO DE ERRADO!;
			hibernate.rollbackTransacao();
		} catch ( Exception e ) {
			e.printStackTrace();
			//			-DESFAZER A TRANSAÇÃO, CASO DE ERRADO!;
			hibernate.rollbackTransacao();

		}

	}
}
