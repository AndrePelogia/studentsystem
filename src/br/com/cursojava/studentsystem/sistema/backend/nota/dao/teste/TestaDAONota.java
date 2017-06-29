package br.com.cursojava.studentsystem.sistema.backend.nota.dao.teste;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.cursojava.studentsystem.connection.HibernateConnection;
import br.com.cursojava.studentsystem.exception.ApplicationException;
import br.com.cursojava.studentsystem.sistema.backend.aluno.model.AlunoPO;
import br.com.cursojava.studentsystem.sistema.backend.materia.model.MateriaPO;
import br.com.cursojava.studentsystem.sistema.backend.nota.dao.NotaDAO;
import br.com.cursojava.studentsystem.sistema.backend.nota.model.NotaPO;

public class TestaDAONota{

	@Test
	//@Ignore
	public void inserir() {

		NotaDAO notaDAO = new NotaDAO();

		HibernateConnection hibernateConnection = new HibernateConnection();

		try {
			NotaPO notaPO = new NotaPO();

			notaPO.setNota( new BigDecimal( "7.3" ) );
			notaPO.setObservacao( "Aluno muito bom" );
			notaPO.setData( new Date() );

			MateriaPO materiaPO = new MateriaPO();

			materiaPO.setNome( "Matemática" );
			materiaPO.setProfessor( "Sueli Matias" );

			notaPO.setMateria( materiaPO );

			AlunoPO alunoPO = new AlunoPO();
			alunoPO.setId( 1L );

			notaPO.setAluno( alunoPO );

			hibernateConnection.iniciarTransacao();
			hibernateConnection.inserir( notaPO );
			hibernateConnection.commitTransacao();

			System.out.println( notaPO );

			ArrayList< NotaPO > notasEncontradas = (ArrayList< NotaPO >) notaDAO.filtrarTodos();
			System.out.println( notasEncontradas );

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			//			-DESFAZER A TRANSAÇÃO, CASO DE ERRADO!;
			hibernateConnection.rollbackTransacao();
		} catch ( Exception e ) {
			e.printStackTrace();
			//			-DESFAZER A TRANSAÇÃO, CASO DE ERRADO!;
			hibernateConnection.rollbackTransacao();

		}
	}

	@Test
	@Ignore
	public void alterar() {

		NotaDAO notaDAO = new NotaDAO();

		HibernateConnection hibernateConnection = new HibernateConnection();

		try {
			NotaPO notaEncontradaId = notaDAO.filtrarPorId( 10L );

			notaEncontradaId.setNota( new BigDecimal( "1.8" ) );

			hibernateConnection.iniciarTransacao();
			hibernateConnection.alterar( notaEncontradaId );
			hibernateConnection.commitTransacao();

			System.out.println( notaEncontradaId );

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			//			-DESFAZER A TRANSAÇÃO, CASO DE ERRADO!;
			hibernateConnection.rollbackTransacao();
		} catch ( Exception e ) {
			e.printStackTrace();
			//			-DESFAZER A TRANSAÇÃO, CASO DE ERRADO!;
			hibernateConnection.rollbackTransacao();

		}
	}

	@Test
	@Ignore
	public void excluir() {

		NotaDAO notaDAO = new NotaDAO();

		HibernateConnection hibernateConnection = new HibernateConnection();

		try {
			NotaPO notaEncontradaId = notaDAO.filtrarPorId( 16L );

			hibernateConnection.iniciarTransacao();
			hibernateConnection.deletar( notaEncontradaId );
			hibernateConnection.commitTransacao();

			ArrayList< NotaPO > notasEncontradas = (ArrayList< NotaPO >) notaDAO.filtrarTodos();
			System.out.println( notasEncontradas );

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			//			-DESFAZER A TRANSAÇÃO, CASO DE ERRADO!;
			hibernateConnection.rollbackTransacao();
		} catch ( Exception e ) {
			e.printStackTrace();
			//			-DESFAZER A TRANSAÇÃO, CASO DE ERRADO!;
			hibernateConnection.rollbackTransacao();

		}
	}

	@Test
	@Ignore
	public void filtrar() {

		NotaDAO notaDAO = new NotaDAO();

		HibernateConnection hibernateConnection = new HibernateConnection();

		try {
			NotaPO notaPO = new NotaPO();
			notaPO.setNota( new BigDecimal( "7.80" ) );

			List< NotaPO > achado = notaDAO.filtrar( notaPO );
			if ( achado.isEmpty() ) {
				System.out.println( "Nota não encontrada!" );
			} else {
				System.out.println( achado );
			}

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			//			-DESFAZER A TRANSAÇÃO, CASO DE ERRADO!;
			hibernateConnection.rollbackTransacao();
		} catch ( Exception e ) {
			e.printStackTrace();
			//			-DESFAZER A TRANSAÇÃO, CASO DE ERRADO!;
			hibernateConnection.rollbackTransacao();

		}
	}
}
