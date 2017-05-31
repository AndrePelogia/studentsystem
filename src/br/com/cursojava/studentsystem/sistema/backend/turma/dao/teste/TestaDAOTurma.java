package br.com.cursojava.studentsystem.sistema.backend.turma.dao.teste;

import java.util.List;

import org.junit.Test;

import br.com.cursojava.studentsystem.connection.HibernateConnection;
import br.com.cursojava.studentsystem.exception.ApplicationException;
import br.com.cursojava.studentsystem.sistema.backend.turma.dao.TurmaDAO;
import br.com.cursojava.studentsystem.sistema.backend.turma.model.TurmaPO;
import br.com.cursojava.studentsystem.utilidades.Utilidades;

public class TestaDAOTurma{
	@Test
	public void executar() {
		TurmaDAO turmaDAO = new TurmaDAO();
		HibernateConnection hibernateConnection = new HibernateConnection();

		try {
			hibernateConnection.iniciarTransacao();
			//INSERIR 
			TurmaPO turmaPO = new TurmaPO();

			turmaPO.setNome( "PHP Diur 1" );
			turmaPO.setDataInicio( Utilidades.parseDate( "22/11/1990" ) );
			turmaPO.setDataTermino( Utilidades.parseDate( "15/01/1991" ) );
			turmaDAO.inserir( turmaPO, hibernateConnection );
			System.out.println( turmaPO );

			//FILTRAR TODOS
			List< TurmaPO > encontrados = turmaDAO.filtrarTodos();
			System.out.println( encontrados );

			//FILTRAR POR ID
			TurmaPO encontradoPorId = turmaDAO.filtrarPorId( 3L );

			//ALTERAR
			encontradoPorId.setDataTermino( Utilidades.parseDate( "18/01/1991" ) );
			turmaDAO.alterar( encontradoPorId, hibernateConnection );

			//FILTRAR POR ID
			encontradoPorId = turmaDAO.filtrarPorId( 1L );

			//DELETAR
			turmaDAO.excluir( encontradoPorId, hibernateConnection );

			//FILTRAR
			turmaPO.setNome( "PHP" );
			List< TurmaPO > listaTurma = turmaDAO.filtrar( turmaPO );
			System.out.println( listaTurma );

			hibernateConnection.commitTransacao();

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			hibernateConnection.rollbackTransacao();
		} catch ( Exception e ) {
			e.printStackTrace();
			hibernateConnection.rollbackTransacao();
		}

	}

}
