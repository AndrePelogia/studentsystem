package br.com.cursojava.studentsystem.connection.teste;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import br.com.cursojava.studentsystem.connection.HibernateConnection;
import br.com.cursojava.studentsystem.exception.ApplicationException;
import br.com.cursojava.studentsystem.sistema.backend.aluno.model.AlunoPO;
import br.com.cursojava.studentsystem.sistema.backend.endereco.model.EnderecoPO;
import br.com.cursojava.studentsystem.utilidades.Utilidades;

public class TestaConexao{
	@Test
	public void executar() {
		HibernateConnection hibernate = new HibernateConnection();

		try {

			/*O básico para o uso do COMPONENTE HibernateConnection é:
			 * -Iniciar transação;
			 * -Fazer o que tem que fazer;
			 * -Confirmar a transação, caso de certo!;
			 * -Desfazer a transação, caso de errado!;*/

			hibernate.iniciarTransacao();

			List encontrados = hibernate.filtrarTodos( AlunoPO.class );
			System.out.println( encontrados );

			AlunoPO aluno = new AlunoPO();
			aluno.setNome( "André Duarte" );
			aluno.setSexo( "Masculino" );
			aluno.setCpf( "387.739.318-70" );
			aluno.setDataNascimento( Utilidades.parseDate( "18/11/1989" ) );

			EnderecoPO enderecoPO = new EnderecoPO();
			enderecoPO.setLogradouro( "Rua Mauricio Biondo Neto" );
			enderecoPO.setNumero( "674" );
			enderecoPO.setBairro( "COHAB" );
			enderecoPO.setCidade( "Ourinhos" );
			enderecoPO.setCep( "19905-280" );
			enderecoPO.setUf( "SP" );

			aluno.setEndereco( enderecoPO );

			aluno.setRa( "12345678" );
			aluno.setPeso( new BigDecimal( "65.0" ) );
			aluno.setAltura( new BigDecimal( "1.70" ) );
			aluno.setTurma( "Turma XXXI Noturna de Seg a Sex" );

			aluno = (AlunoPO) hibernate.inserir( aluno );

			encontrados = hibernate.filtrarTodos( AlunoPO.class );
			System.out.println( encontrados );

			aluno.setTurma( "Sem turma" );
			hibernate.alterar( aluno );

			encontrados = hibernate.filtrarTodos( AlunoPO.class );
			System.out.println( encontrados );

			AlunoPO encontradoPorId = (AlunoPO) hibernate.filtrarPorId( AlunoPO.class, aluno.getId() );
			System.out.println( encontradoPorId );

			hibernate.deletar( aluno );

			encontrados = hibernate.filtrarTodos( AlunoPO.class );
			System.out.println( encontrados );

			hibernate.commitTransacao();

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			hibernate.rollbackTransacao();
		} catch ( Exception e ) {
			e.printStackTrace();
			hibernate.rollbackTransacao();

		}

	}
}
