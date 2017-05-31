package br.com.cursojava.studentsystem.sistema.backend.bicicleta.dao.teste;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import br.com.cursojava.studentsystem.connection.HibernateConnection;
import br.com.cursojava.studentsystem.exception.ApplicationException;
import br.com.cursojava.studentsystem.sistema.backend.aluno.dao.AlunoDAO;
import br.com.cursojava.studentsystem.sistema.backend.aluno.model.AlunoPO;
import br.com.cursojava.studentsystem.sistema.backend.bicicleta.dao.BicicletaDAO;
import br.com.cursojava.studentsystem.sistema.backend.bicicleta.model.BicicletaPO;
import br.com.cursojava.studentsystem.utilidades.Utilidades;

public class TestaDAOBicicleta{
	@Test
	public void executar() {

		BicicletaDAO bicicletaDAO = new BicicletaDAO();
		AlunoDAO alunoDAO = new AlunoDAO();

		HibernateConnection hibernate = new HibernateConnection();

		try {

			/*O b�sico para o uso do COMPONENTE HibernateConnection �:
			 * -Iniciar transa��o;
			 * -Fazer o que tem que fazer;
			 * -Confirmar a transa��o, caso de certo!;
			 * -Desfazer a transa��o, caso de errado!;*/

			//			-INICIAR TRANSA��O;
			hibernate.iniciarTransacao();

			//			-FAZER O QUE TEM QUE FAZER;
			List< BicicletaPO > encontrados = bicicletaDAO.filtrarTodos();
			System.out.println( encontrados );

			BicicletaPO bicicleta = new BicicletaPO();
			bicicleta.setCor( "Branca" );
			bicicleta.setDataFabricacao( Utilidades.parseDate( "11/10/1997" ) );
			bicicleta.setMarca( "Monark" );
			bicicleta.setMarcha( true );
			bicicleta.setMaterial( "A�o" );
			bicicleta.setModalidade( "Street" );
			bicicleta.setPreco( new BigDecimal( "500.00" ) );

			AlunoPO alunoId = alunoDAO.filtrarPorId( 1L );
			bicicleta.setAluno( alunoId );

			bicicletaDAO.inserir( bicicleta, hibernate );

			encontrados = bicicletaDAO.filtrarTodos();
			System.out.println( encontrados );

			BicicletaPO encontradoPorId = bicicletaDAO.filtrarPorId( bicicleta.getId() );

			encontradoPorId.setCor( "Vermelha" );
			bicicletaDAO.alterar( encontradoPorId, hibernate );

			encontrados = bicicletaDAO.filtrarTodos();
			System.out.println( encontrados );

			encontradoPorId = bicicletaDAO.filtrarPorId( bicicleta.getId() );
			System.out.println( encontradoPorId );

			//bicicletaDAO.excluir( encontradoPorId, hibernate );

			encontrados = bicicletaDAO.filtrarTodos();
			System.out.println( encontrados );

			//			-CONFIRMAR A TRANSA��O, CASO DE CERTO!;
			hibernate.commitTransacao();

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			//			-DESFAZER A TRANSA��O, CASO DE ERRADO!;
			hibernate.rollbackTransacao();
		} catch ( Exception e ) {
			e.printStackTrace();
			//			-DESFAZER A TRANSA��O, CASO DE ERRADO!;
			hibernate.rollbackTransacao();

		}

	}
}
