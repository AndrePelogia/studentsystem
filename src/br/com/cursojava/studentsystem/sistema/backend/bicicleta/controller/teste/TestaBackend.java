package br.com.cursojava.studentsystem.sistema.backend.bicicleta.controller.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.swing.JOptionPane;

import org.junit.Test;

import br.com.cursojava.studentsystem.exception.ApplicationException;
import br.com.cursojava.studentsystem.sistema.backend.aluno.controller.AlunoFACADE;
import br.com.cursojava.studentsystem.sistema.backend.aluno.model.AlunoPO;
import br.com.cursojava.studentsystem.sistema.backend.bicicleta.controller.BicicletaFACADE;
import br.com.cursojava.studentsystem.sistema.backend.bicicleta.model.BicicletaPO;
import br.com.cursojava.studentsystem.utilidades.Utilidades;

public class TestaBackend{
	@Test
	public void executar() throws ApplicationException {
		try {
			//PREENCHENDO O OBJETO BICICLETA
			BicicletaPO po = new BicicletaPO();
			po.setCor( "Verde" );
			po.setDataFabricacao( Utilidades.parseDate( "23/12/2015" ) );
			po.setMarca( "Caloi" );
			po.setMarcha( true );
			po.setMaterial( "Aluminio" );
			po.setModalidade( "Street" );
			po.setPreco( new BigDecimal( "900.00" ) );

			AlunoFACADE facadeAluno = new AlunoFACADE();
			AlunoPO aluno = facadeAluno.filtrarPorId( 1L );
			po.setAluno( aluno );

			//		PERSISTINDO E FILTRANDO BICICLETA
			BicicletaFACADE facade = new BicicletaFACADE();

			facade.inserir( po );

			BicicletaPO encontrado = facade.filtrarPorId( po.getId() );
			encontrado.setCor( "Vermelho" );
			facade.alterar( encontrado );

			//facade.excluir( encontrado );

			encontrado = facade.filtrarPorId( po.getId() );
			System.out.println( "por ID: " + encontrado );

			List encontrados = facade.filtrarTodos();
			System.out.println( "Todos encontrados:" + encontrados );

			encontrados = facade.filtrar( po );
			System.out.println( "Filtrar: " + encontrados );

			//System.out.println( new char[ ] { 'a', 'b', 'c' } );

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			JOptionPane.showMessageDialog( null, e.getMessage() );
		} catch ( Exception e ) {
			e.printStackTrace();
			JOptionPane.showMessageDialog( null, e.getMessage() );
		}

	}
}
