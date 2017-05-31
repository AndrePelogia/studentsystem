package br.com.cursojava.studentsystem.sistema.backend.aluno.controller.teste;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.junit.Test;

import br.com.cursojava.studentsystem.exception.ApplicationException;
import br.com.cursojava.studentsystem.sistema.backend.aluno.controller.AlunoFACADE;
import br.com.cursojava.studentsystem.sistema.backend.aluno.model.AlunoPO;
import br.com.cursojava.studentsystem.sistema.backend.endereco.model.EnderecoPO;

public class TestaBackEnd{
	@Test
	public void executar() throws ApplicationException {
		try {

			AlunoPO po = new AlunoPO( "André Pelogia Duarte Silva", "387.739.318-70", "Turma XXXI Noturno de Seg a Sex" );

			po.setAltura( new BigDecimal( "1.84" ) );
			//po.setCpf( "" ); JÁ ESTÁ SETANDO NO CONSTURTOR
			po.setDataNascimento( new Date() );

			EnderecoPO endereco = new EnderecoPO();
			endereco.setLogradouro( "Rua Mauricio Biondo Neto" );
			endereco.setNumero( "674" );
			endereco.setBairro( "COHAB" );
			endereco.setCep( "19905-280" );
			endereco.setCidade( "Ourinhos" );
			endereco.setUf( "SP" );
			po.setEndereco( endereco );

			po.setId( Long.valueOf( "1" ) );//pega string e retorna um longão
			//po.setNome( "" );
			po.setPeso( new BigDecimal( "70.00" ) );
			po.setRa( "121016-7" );
			po.setSexo( "Masculino" );
			//po.setTurma( "" );

			//		PERSISTINDO E FILTRANDO ALUNO
			AlunoFACADE facade = new AlunoFACADE();

			facade.inserir( po );

			AlunoPO encontrado = facade.filtrarPorId( po.getId() );
			encontrado.setNome( "André Duarte" );
			facade.alterar( encontrado );

			facade.excluir( encontrado );

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
