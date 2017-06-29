package br.com.cursojava.studentsystem.sistema.backend.nota.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.cursojava.studentsystem.connection.HibernateConnection;
import br.com.cursojava.studentsystem.exception.ApplicationException;
import br.com.cursojava.studentsystem.sistema.backend.materia.model.MateriaPO;
import br.com.cursojava.studentsystem.sistema.backend.nota.model.NotaPO;

public class NotaDAO{
	public void inserir( NotaPO po, HibernateConnection hibernate ) throws ApplicationException {
		try {

			po.setId( hibernate.inserir( po ).getId() );

		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido ao inserir...", e );
		}

	}

	public void alterar( NotaPO po, HibernateConnection hibernate ) throws ApplicationException {

		try {

			hibernate.alterar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido ao alterar...", e );
		}
	}

	public void excluir( NotaPO po, HibernateConnection hibernate ) throws ApplicationException {

		try {
			//System.out.println( "Excluindo: " + po.toString() );
			hibernate.deletar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido ao excluir...", e );
		}
	}

	@SuppressWarnings( "unchecked" )
	public List< NotaPO > filtrarTodos() throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();
		try {
			//System.out.println( "Filtrando tudo:" );

			return hibernate.filtrarTodos( NotaPO.class );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido ao filtrar tudo...", e );
		}
	}

	public NotaPO filtrarPorId( Long id ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();
		try {
			//System.out.println( "Filtrando por ID: " + id );
			return (NotaPO) hibernate.filtrarPorId( NotaPO.class, id );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido ao filtrar por ID...", e );
		}
	}

	public List< NotaPO > filtrar( NotaPO po ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();
			Criteria criteria = hibernate.getSessaoCorrente().createCriteria( NotaPO.class );
			criteria.setResultTransformer( Criteria.DISTINCT_ROOT_ENTITY );

			/*MONTANDO AS RESTRIÇÕES DINÂNAMICAS*/
			if ( po.getId() != null ) {
				criteria.add( Restrictions.idEq( po.getId() ) );
			}

			if ( po.getNota() != null ) {
				criteria.add( Restrictions.eq( "nota", po.getNota() ) );
			}

			if ( po.getObservacao() != null ) {
				criteria.add( Restrictions.like( "observacao", po.getObservacao(), MatchMode.START ) );
			}

			if ( po.getData() != null ) {
				criteria.add( Restrictions.eq( "data", po.getData() ) );
			}
			//DADOS DA MATERIA
			if ( po.getMateria() != null ) {
				criteria.createAlias( "materia", "materia" );

				if ( po.getMateria().getId() != null ) {
					criteria.add( Restrictions.eq( "materia.id", po.getMateria().getId() ) );
				}

				if ( po.getMateria().getNome() != null ) {
					criteria.add( Restrictions.like( "materia.nome", po.getMateria().getNome(), MatchMode.START ) );
				}

				if ( po.getMateria().getProfessor() != null ) {
					criteria.add( Restrictions.like( "materia.professor", po.getMateria().getProfessor(), MatchMode.START ) );
				}

			} //FIM DADOS MATERIA

			//DADOS ALUNO

			/*DADOS DO ALUNO, UTILIZO O ALIAS PARA NAVEGAR ATÉ OS CAMPOS DO ALUNO, UTILIZO NO HORA DE PESQUISAR.
			EX.: QUANDO PESQUISO PELO ALUNO, EXIBE NA TABELA AS BIKES DELES.*/
			if ( po.getAluno() != null ) {
				criteria.createAlias( "aluno", "aluno" );
				if ( po.getAluno().getId() != null ) {
					criteria.add( Restrictions.idEq( po.getAluno().getId() ) );
				}
			}

			criteria.addOrder( Order.asc( "nota" ) );
			List encontrados = criteria.list();
			hibernate.commitTransacao();
			return encontrados;

		} catch ( ApplicationException e ) {
			/*4º DESFAZER A TRANSAÇÃO, CASO DE ERRADO*/
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			throw new ApplicationException( "Erro desconhecido ao filtrar...", e );
		}
	}

	public List< MateriaPO > filtrarMateriaPorNome( String nome ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();
			Criteria criteria = hibernate.getSessaoCorrente().createCriteria( MateriaPO.class );
			criteria.setResultTransformer( Criteria.DISTINCT_ROOT_ENTITY );

			/*MONTANDO AS RESTRIÇÕES DINÂNAMICAS*/

			if ( nome != null && !nome.trim().isEmpty() ) {
				criteria.add( Restrictions.like( "nome", nome, MatchMode.START ) );
			}

			criteria.addOrder( Order.asc( "nome" ) );

			List encontrados = criteria.list();

			hibernate.commitTransacao();

			return encontrados;

		} catch ( ApplicationException e ) {
			/*4º DESFAZER A TRANSAÇÃO, CASO DE ERRADO*/
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			throw new ApplicationException( "Erro desconhecido ao filtrar...", e );
		}
	}
}
