package br.com.cursojava.studentsystem.sistema.backend.aluno.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.cursojava.studentsystem.connection.HibernateConnection;
import br.com.cursojava.studentsystem.exception.ApplicationException;
import br.com.cursojava.studentsystem.sistema.backend.aluno.model.AlunoPO;

public class AlunoDAO{
	public void inserir( AlunoPO po, HibernateConnection hibernate ) throws ApplicationException {
		try {

			po.setId( hibernate.inserir( po ).getId() );

		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido ao inserir...", e );
		}

	}

	public void alterar( AlunoPO po, HibernateConnection hibernate ) throws ApplicationException {

		try {

			hibernate.alterar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido ao alterar...", e );
		}
	}

	public void excluir( AlunoPO po, HibernateConnection hibernate ) throws ApplicationException {

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
	public List< AlunoPO > filtrarTodos() throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();
		try {
			//System.out.println( "Filtrando tudo:" );

			return hibernate.filtrarTodos( AlunoPO.class );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido ao filtrar tudo...", e );
		}
	}

	public AlunoPO filtrarPorId( Long id ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();
		try {
			//System.out.println( "Filtrando por ID: " + id );
			return (AlunoPO) hibernate.filtrarPorId( AlunoPO.class, id );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido ao filtrar por ID...", e );
		}
	}

	public List< AlunoPO > filtrar( AlunoPO po ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {
			hibernate.iniciarTransacao();
			Criteria criteria = hibernate.getSessaoCorrente().createCriteria( AlunoPO.class );
			criteria.setResultTransformer( Criteria.DISTINCT_ROOT_ENTITY );

			/*MONTANDO AS RESTRIÇÕES DINÂNAMICAS*/
			if ( po.getAltura() != null ) {
				criteria.add( Restrictions.eq( "altura", po.getAltura() ) );
			}

			if ( po.getCpf() != null ) {
				criteria.add( Restrictions.eq( "cpf", po.getCpf() ) );
			}

			if ( po.getNome() != null ) {
				criteria.add( Restrictions.like( "nome", po.getNome(), MatchMode.START ) );
			}
			//DADOS DO ENDEREÇO
			if ( po.getEndereco() != null ) {
				criteria.createAlias( "endereco", "endereco" );
				if ( po.getEndereco().getBairro() != null ) {
					criteria.add( Restrictions.like( "endereco.bairro", po.getEndereco().getBairro(), MatchMode.START ) );
				}

				if ( po.getEndereco().getCep() != null ) {
					criteria.add( Restrictions.like( "endereco.cep", po.getEndereco().getCep(), MatchMode.START ) );
				}

				if ( po.getEndereco().getCidade() != null ) {
					criteria.add( Restrictions.like( "endereco.cidade", po.getEndereco().getCidade(), MatchMode.START ) );
				}

				if ( po.getEndereco().getId() != null ) {
					criteria.add( Restrictions.eq( "endereco.id", po.getEndereco().getId() ) );
				}
				if ( po.getEndereco().getLogradouro() != null ) {
					criteria.add( Restrictions.like( "endereco.logradouro", po.getEndereco().getLogradouro(), MatchMode.START ) );
				}

				if ( po.getEndereco().getNumero() != null ) {
					criteria.add( Restrictions.like( "endereco.numero", po.getEndereco().getNumero(), MatchMode.START ) );
				}

				if ( po.getEndereco().getUf() != null ) {
					criteria.add( Restrictions.eq( "endereco.uf", po.getEndereco().getUf() ) );
				}
			} //FIM DADOS ENDEREÇO

			if ( po.getDataNascimento() != null ) {
				criteria.add( Restrictions.eq( "dataNascimento", po.getDataNascimento() ) );
			}

			if ( po.getId() != null ) {
				criteria.add( Restrictions.idEq( po.getId() ) );
			}

			if ( po.getPeso() != null ) {
				criteria.add( Restrictions.eq( "peso", po.getPeso() ) );
			}

			if ( po.getRa() != null ) {
				criteria.add( Restrictions.eq( "ra", po.getRa() ) );
			}

			if ( po.getSexo() != null ) {
				criteria.add( Restrictions.eq( "sexo", po.getSexo() ) );
			}

			//DADOS TURMA
			if ( po.getTurma() != null ) {
				criteria.createAlias( "turma", "turma" );
				if ( po.getTurma().getId() != null ) {
					criteria.add( Restrictions.idEq( po.getTurma().getId() ) );
				}
				if ( po.getTurma().getNome() != null ) {
					criteria.add( Restrictions.like( "turma.nome", po.getTurma().getNome(), MatchMode.START ) );
				}
				if ( po.getTurma().getDataInicio() != null ) {
					criteria.add( Restrictions.eq( "dataInicio", po.getTurma().getDataInicio() ) );
				}
				if ( po.getTurma().getDataTermino() != null ) {
					criteria.add( Restrictions.eq( "dataTermino", po.getTurma().getDataTermino() ) );
				}

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

	public List filtrarPorIntervaloDataNascimento( Date dataInicial, Date dataFinal, HibernateConnection hibernate ) throws ApplicationException {
		try {
			/*CRIANDO UMA HQL*/
			final StringBuffer HQL = new StringBuffer();
			HQL.append( " SELECT aluno FROM AlunoPO aluno" );
			HQL.append( " WHERE " );
			HQL.append( " aluno.dataNascimento BETHWEEN :dataInicialParam AND :dataFinalParam" );

			/*CRIANDO UM OBJETO DO TIPO QUERY*/
			Query query = hibernate.getSessaoCorrente().createQuery( HQL.toString() );

			/*PREENCHENDO OS PARÂMETROS DA HQL ACIMA*/
			query.setParameter( "dataInicialParam", dataInicial );
			query.setParameter( "dataFinalParam", dataFinal );

			/*EXECUTANDO A QUERY*/
			return query.list();
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido ao filtrar...", e );
		}
	}
}
