package br.com.cursojava.studentsystem.connection.teste;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import br.com.cursojava.studentsystem.connection.HibernateConnection;
import br.com.cursojava.studentsystem.exception.ApplicationException;
import br.com.cursojava.studentsystem.sistema.backend.aluno.model.AlunoPO;
import br.com.cursojava.studentsystem.sistema.backend.endereco.model.EnderecoPO;
import br.com.cursojava.studentsystem.sistema.backend.turma.model.TurmaPO;
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

			List encontrados = new HibernateConnection().filtrarTodos( AlunoPO.class );
			System.out.println( encontrados );

			AlunoPO aluno = new AlunoPO();
			aluno.setNome( "Mariana Ximenes" );
			aluno.setSexo( "Feminino" );
			aluno.setCpf( "427.255.830-71" );
			aluno.setDataNascimento( Utilidades.parseDate( "18/11/1988" ) );

			EnderecoPO enderecoPO = new EnderecoPO();
			enderecoPO.setLogradouro( "Rua Das Esmeraldas" );
			enderecoPO.setNumero( "12" );
			enderecoPO.setBairro( "COHAB" );
			enderecoPO.setCidade( "São Paulo" );
			enderecoPO.setCep( "22000-280" );
			enderecoPO.setUf( "SP" );

			aluno.setEndereco( enderecoPO );

			aluno.setRa( "78912345" );
			aluno.setPeso( new BigDecimal( "65.0" ) );
			aluno.setAltura( new BigDecimal( "1.70" ) );

			TurmaPO encontradoID = (TurmaPO) new HibernateConnection().filtrarPorId( TurmaPO.class, 1L );
			aluno.setTurma( encontradoID );

			hibernate.iniciarTransacao();
			aluno = (AlunoPO) hibernate.inserir( aluno );
			hibernate.commitTransacao();

			encontrados = new HibernateConnection().filtrarTodos( AlunoPO.class );
			System.out.println( encontrados );

			//hibernate.alterar( aluno );

			/*encontrados = hibernate.filtrarTodos( AlunoPO.class );
			System.out.println( encontrados );
			
			AlunoPO encontradoPorId = (AlunoPO) hibernate.filtrarPorId( AlunoPO.class, aluno.getId() );
			System.out.println( encontradoPorId );
			
			//hibernate.deletar( aluno );
			
			encontrados = hibernate.filtrarTodos( AlunoPO.class );
			System.out.println( encontrados );*/

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			hibernate.rollbackTransacao();
		} catch ( Exception e ) {
			e.printStackTrace();
			hibernate.rollbackTransacao();

		}

	}
}
