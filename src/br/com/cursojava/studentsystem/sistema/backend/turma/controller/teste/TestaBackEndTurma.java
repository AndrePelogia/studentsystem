package br.com.cursojava.studentsystem.sistema.backend.turma.controller.teste;

import javax.swing.JOptionPane;

import org.junit.Test;

import br.com.cursojava.studentsystem.exception.ApplicationException;
import br.com.cursojava.studentsystem.sistema.backend.turma.controller.TurmaFACADE;
import br.com.cursojava.studentsystem.sistema.backend.turma.model.TurmaPO;

public class TestaBackEndTurma{

	@Test
	public void executar() throws ApplicationException {
		try {
			TurmaPO turmaPO = new TurmaPO();
			TurmaFACADE turmaFACADE = new TurmaFACADE();

			/*turmaPO.setNome( "Python" );
			turmaPO.setDataInicio( new Date() );
			turmaPO.setDataTermino( Utilidades.parseDate( "25/07/2017" ) );
			turmaFACADE.inserir( turmaPO );*/

			TurmaPO encontrado = turmaFACADE.filtrarPorId( 5l );
			System.out.println( encontrado );

			//turmaFACADE.excluir( encontrado );

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			JOptionPane.showMessageDialog( null, e.getMessage() );
		} /*catch ( ParseException e ) {
		  e.printStackTrace();
		  }*/ catch ( Exception e ) {
			e.printStackTrace();
			JOptionPane.showMessageDialog( null, e.getMessage() );
		}
	}
}
