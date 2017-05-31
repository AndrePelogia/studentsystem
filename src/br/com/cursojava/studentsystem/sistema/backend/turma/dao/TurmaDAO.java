package br.com.cursojava.studentsystem.sistema.backend.turma.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.cursojava.studentsystem.connection.HibernateConnection;
import br.com.cursojava.studentsystem.exception.ApplicationException;
import br.com.cursojava.studentsystem.sistema.backend.turma.model.TurmaPO;

public class TurmaDAO{
	public void inserir( TurmaPO po, HibernateConnection hibernate ) throws ApplicationException {
		try {
			po.setId( hibernate.inserir( po ).getId() );

		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido ao inserir", e );
		}
	}

	public void alterar( TurmaPO po, HibernateConnection hibernate ) throws ApplicationException {
		try {
			hibernate.alterar( po );

		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido ao alterar", e );
		}
	}

	public void excluir( TurmaPO po, HibernateConnection hibernate ) throws ApplicationException {
		try {
			hibernate.deletar( po );

		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido ao excluir", e );
		}
	}

	@SuppressWarnings( "unchecked" )
	public List< TurmaPO > filtrarTodos() throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();
		try {
			List< TurmaPO > list = hibernate.filtrarTodos( TurmaPO.class );
			return list;
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido ao filtrar todos", e );
		}
	}

	public TurmaPO filtrarPorId( Long id ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();
		try {
			TurmaPO turma = (TurmaPO) hibernate.filtrarPorId( TurmaPO.class, id );
			return turma;
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido ao filtrar por ID...", e );
		}
	}

	public List< TurmaPO > filtrar( TurmaPO po ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();
			Criteria criteria = hibernate.getSessaoCorrente().createCriteria( TurmaPO.class );
			criteria.setResultTransformer( Criteria.DISTINCT_ROOT_ENTITY );

			/*MONTANDO AS RESTRIÇÕES DINÂNAMICAS*/

			if ( po.getNome() != null ) {
				criteria.add( Restrictions.like( "nome", po.getNome(), MatchMode.START ) );
			}

			if ( po.getId() != null ) {
				criteria.add( Restrictions.idEq( po.getId() ) );
			}

			if ( po.getDataInicio() != null ) {
				criteria.add( Restrictions.eq( "dataInicio", po.getDataInicio() ) );
			}

			if ( po.getDataTermino() != null ) {
				criteria.add( Restrictions.eq( "dataTermino", po.getDataTermino() ) );
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
