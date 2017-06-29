package br.com.cursojava.studentsystem.sistema.backend.aluno.dao.teste;

import java.util.List;

import org.junit.Test;

import br.com.cursojava.studentsystem.connection.HibernateConnection;
import br.com.cursojava.studentsystem.exception.ApplicationException;
import br.com.cursojava.studentsystem.sistema.backend.aluno.dao.AlunoDAO;
import br.com.cursojava.studentsystem.sistema.backend.aluno.model.AlunoPO;
import br.com.cursojava.studentsystem.sistema.backend.turma.dao.TurmaDAO;

public class TestaDAO{
	@Test
	public void executar() {

		AlunoDAO alunoDAO = new AlunoDAO();
		TurmaDAO turmaDAO = new TurmaDAO();

		HibernateConnection hibernate = new HibernateConnection();

		try {

			/*O básico para o uso do COMPONENTE HibernateConnection é:
			 * -Iniciar transação;
			 * -Fazer o que tem que fazer;
			 * -Confirmar a transação, caso de certo!;
			 * -Desfazer a transação, caso de errado!;*/

			//			-FAZER O QUE TEM QUE FAZER;
			List< AlunoPO > encontrados = alunoDAO.filtrarTodos();
			System.out.println( encontrados );

			/** ESTÁ COMENTADO PORQUE ESTAVA TESTANDO O ATUALIZAR */
			//DADOS DO ALUNO
			/*AlunoPO aluno = new AlunoPO();
			aluno.setNome( "Gustavo Borges" );
			aluno.setSexo( "Masculino" );
			aluno.setCpf( "269.319.259-54" );
			aluno.setDataNascimento( Utilidades.parseDate( "15/01/1995" ) );
			aluno.setRa( "123789" );
			aluno.setPeso( new BigDecimal( "70.0" ) );
			aluno.setAltura( new BigDecimal( "1.77" ) );
			
			//DADOS DO ENDERÇO DO ALUNO
			EnderecoPO endereco = new EnderecoPO();
			endereco.setLogradouro( "Rua Nove" );
			endereco.setNumero( "777" );
			endereco.setBairro( "COHAB" );
			endereco.setCep( "19905-280" );
			endereco.setCidade( "Ourinhos" );
			endereco.setUf( "SP" );
			
			aluno.setEndereco( endereco );
			
			OBS: QUANDO FOR DESCOMENTADO ESSA LINHA BUSCARÁ NO BANCO A TURMA ID 1!!!
			TurmaPO encontradoID = turmaDAO.filtrarPorId( 1L );
			
			OBS: QUANDO FOR DESCOMENTADO ESSA LINHA IRÁ INSERIR NO ALUNO A TURMA!!!
			aluno.setTurma( encontradoID );*/

			/** ESTA LINHA BUSCA O ALUNO DE ID 3, PARA QUE POSSA SER FEITA A ALTERAÇÃO. */
			AlunoPO encontradoPorId = alunoDAO.filtrarPorId( 3L );

			//	-INICIAR TRANSAÇÃO
			hibernate.iniciarTransacao();
			//alunoDAO.inserir( aluno, hibernate );

			encontradoPorId.setNome( "Mariana Linda Ximenes" );
			alunoDAO.alterar( encontradoPorId, hibernate );

			// -CONFIRMAR A TRANSAÇÃO, CASO DE CERTO!
			hibernate.commitTransacao();
			System.out.println( encontradoPorId );

			/*encontrados = alunoDAO.filtrarTodos();
			System.out.println( encontrados );
						
			encontrados = alunoDAO.filtrarTodos();
			System.out.println( encontrados );
			
			encontradoPorId = alunoDAO.filtrarPorId( aluno.getId() );
			System.out.println( encontradoPorId );
			
			//alunoDAO.excluir( encontradoPorId );
			
			encontrados = alunoDAO.filtrarTodos();
			System.out.println( encontrados );*/

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
