package br.com.cursojava.studentsystem.sistema.backend.bicicleta.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.cursojava.studentsystem.connection.HibernateConnection;
import br.com.cursojava.studentsystem.exception.ApplicationException;
import br.com.cursojava.studentsystem.sistema.backend.bicicleta.model.BicicletaPO;

public class BicicletaDAO{

	public void inserir( BicicletaPO po, HibernateConnection hibernate ) throws ApplicationException {
		try {
			po.setId( hibernate.inserir( po ).getId() );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido ao inserir...", e );
		}
	}

	public void alterar( BicicletaPO po, HibernateConnection hibernate ) throws ApplicationException {
		try {
			hibernate.alterar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido ao alterar...", e );
		}
	}

	public void excluir( BicicletaPO po, HibernateConnection hibernate ) throws ApplicationException {
		try {
			hibernate.deletar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido ao excluir...", e );
		}
	}

	@SuppressWarnings( "unchecked" )
	public List< BicicletaPO > filtrarTodos() throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();
		try {
			return hibernate.filtrarTodos( BicicletaPO.class );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido ao filtrar tudo...", e );
		}
	}

	public BicicletaPO filtrarPorId( Long id ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();
		try {
			return (BicicletaPO) hibernate.filtrarPorId( BicicletaPO.class, id );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido ao filtrar por ID...", e );
		}
	}

	@SuppressWarnings( "unchecked" )
	public List< BicicletaPO > filtrar( BicicletaPO po ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();
			Criteria criteria = hibernate.getSessaoCorrente().createCriteria( BicicletaPO.class );
			criteria.setResultTransformer( Criteria.DISTINCT_ROOT_ENTITY );

			if ( po.getId() != null ) {
				criteria.add( Restrictions.idEq( po.getId() ) );
			}

			if ( po.getMarcha() != null ) {
				criteria.add( Restrictions.eq( "marcha", po.getMarcha() ) );
			}

			if ( po.getCor() != null ) {
				criteria.add( Restrictions.like( "cor", po.getCor(), MatchMode.START ) );
			}

			if ( po.getDataFabricacao() != null ) {
				criteria.add( Restrictions.eq( "dataFabricacao", po.getDataFabricacao() ) );
			}

			if ( po.getMarca() != null ) {
				criteria.add( Restrictions.like( "marca", po.getMarca(), MatchMode.START ) );
			}

			if ( po.getMaterial() != null ) {
				criteria.add( Restrictions.like( "material", po.getMaterial(), MatchMode.START ) );
			}

			if ( po.getModalidade() != null ) {
				criteria.add( Restrictions.like( "modalidade", po.getModalidade(), MatchMode.START ) );
			}

			if ( po.getPreco() != null ) {
				criteria.add( Restrictions.eq( "preco", po.getPreco() ) );
			}

			criteria.addOrder( Order.asc( "marca" ) );
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
