package br.com.cursojava.studentsystem.sistema.backend.nota.controller.teste;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.junit.Ignore;
import org.junit.Test;

import br.com.cursojava.studentsystem.exception.ApplicationException;
import br.com.cursojava.studentsystem.sistema.backend.aluno.controller.AlunoFACADE;
import br.com.cursojava.studentsystem.sistema.backend.aluno.model.AlunoPO;
import br.com.cursojava.studentsystem.sistema.backend.materia.model.MateriaPO;
import br.com.cursojava.studentsystem.sistema.backend.nota.controller.NotaFACADE;
import br.com.cursojava.studentsystem.sistema.backend.nota.model.NotaPO;

public class TestaBackEndNota{

	NotaFACADE notaFACADE = new NotaFACADE();
	AlunoFACADE alunoFACADE = new AlunoFACADE();

	@Test
	//@Ignore
	public void inserir() {

		try {
			NotaPO notaPO = new NotaPO();

			notaPO.setNota( new BigDecimal( "10" ) );
			notaPO.setObservacao( "S/N" );
			notaPO.setData( new Date() );

			MateriaPO materiaPO = new MateriaPO();

			materiaPO.setNome( "Química" );
			materiaPO.setProfessor( "Elis Maria" );

			notaPO.setMateria( materiaPO );

			AlunoPO alunoPO = alunoFACADE.filtrarPorId( 5L );

			notaPO.setAluno( alunoPO );

			notaFACADE.inserir( notaPO );

			System.out.println( notaPO );

			ArrayList< NotaPO > notasEncontradas = notaFACADE.filtrarTodos();
			System.out.println( notasEncontradas );

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			JOptionPane.showMessageDialog( null, e.getMessage() );
		} catch ( Exception e ) {
			e.printStackTrace();
			JOptionPane.showMessageDialog( null, e.getMessage() );
		}
	}

	@Test
	@Ignore
	public void alterar() {

		try {
			NotaPO poEncontrado = notaFACADE.filtrarPorId( 2L );

			poEncontrado.setNota( new BigDecimal( "7.1" ) );

			notaFACADE.alterar( poEncontrado );

			System.out.println( poEncontrado );

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			JOptionPane.showMessageDialog( null, e.getMessage() );
		} catch ( Exception e ) {
			e.printStackTrace();
			JOptionPane.showMessageDialog( null, e.getMessage() );
		}
	}

	@Test
	@Ignore
	public void excluir() {
		/**
		 * Criei uma regra, que senão encontrar o PO ele exibe a msg "Objeto não exite";
		 * Se existir foi declarado uma variável dialogButton do tipo inteiro que recebera Sim ou Não;
		 * Caso SIM o objeto é excluído e mostra a msg de sucesso!, caso NÃO a msg de aviso!S
		 */
		try {
			NotaPO poEncontrado = notaFACADE.filtrarPorId( 7L );

			if ( poEncontrado != null ) {
				int dialogButton = JOptionPane.showConfirmDialog( null, "Quer realemente excluir?\n" + poEncontrado, "ATENÇÃO!!!", JOptionPane.YES_NO_OPTION );
				if ( dialogButton == JOptionPane.YES_OPTION ) {
					notaFACADE.excluir( poEncontrado );
					JOptionPane.showMessageDialog( null, "Excluido com sucesso!" );
				} else {
					JOptionPane.showMessageDialog( null, "Objeto não excluído!" );
				}
			} else {
				JOptionPane.showMessageDialog( null, "Objeto não existe!" );
			}
		} catch ( ApplicationException e ) {
			e.printStackTrace();
			JOptionPane.showMessageDialog( null, e.getMessage() );
		} catch ( Exception e ) {
			e.printStackTrace();
			JOptionPane.showMessageDialog( null, e.getMessage() );
		}
	}

	@Test
	@Ignore
	public void filtrar() {

		try {
			NotaPO notaPO = new NotaPO();
			notaPO.setNota( new BigDecimal( "9.30" ) );

			List< NotaPO > achado = notaFACADE.filtrar( notaPO );
			if ( achado.isEmpty() ) {
				JOptionPane.showMessageDialog( null, "Não existe nenhum dado com o valor informado!" );
			} else {
				System.out.println( achado );
			}

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			JOptionPane.showMessageDialog( null, e.getMessage() );
		} catch ( Exception e ) {
			e.printStackTrace();
			JOptionPane.showMessageDialog( null, e.getMessage() );
		}
	}
}
