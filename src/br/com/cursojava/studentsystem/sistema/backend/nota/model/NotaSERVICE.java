package br.com.cursojava.studentsystem.sistema.backend.nota.model;

import java.util.ArrayList;
import java.util.List;

import br.com.cursojava.studentsystem.connection.HibernateConnection;
import br.com.cursojava.studentsystem.exception.ApplicationException;
import br.com.cursojava.studentsystem.interfaces.Crud;
import br.com.cursojava.studentsystem.sistema.backend.materia.model.MateriaPO;
import br.com.cursojava.studentsystem.sistema.backend.nota.dao.NotaDAO;

public class NotaSERVICE implements Crud< NotaPO >{

	//GARANTE A APLICAÇÃO DA ASSOCIAÇÃO ENTRE O SERVICE E O DAO
	private final NotaDAO DAO;

	//CONSTRUTOR RESPONSÁVEL POR INICIALIZAR O ATRIBUTO DAO.
	public NotaSERVICE(){
		DAO = new NotaDAO();
	}

	@Override
	public void inserir( NotaPO po ) throws ApplicationException {
		HibernateConnection hibernateConnection = new HibernateConnection();
		try {
			if ( po == null ) {
				throw new ApplicationException( "Parâmetro não pode ser NULO!" );
			}

			if ( po.getNota() == null ) {
				throw new ApplicationException( "Nota não pode ser NULA!" );
			}

			if ( po.getMateria().getNome() == null && po.getMateria().getId() == null ) {
				throw new ApplicationException( "Matéria não Informada!" );
			} else {
				//FOI FEITO ISSO, POIS PARA INSERIR MATERIA PRECISO ANTES FILTRAR.
				po.setMateria( (MateriaPO) hibernateConnection.filtrarPorId( MateriaPO.class, po.getMateria().getId() ) );
			}

			if ( po.getAluno().getId() == null ) {
				throw new ApplicationException( "Aluno não informado!" );
			}

			if ( po.getData() == null ) {
				throw new ApplicationException( "Data não pode ser NULO!" );
			}

			hibernateConnection.iniciarTransacao();
			DAO.inserir( po, hibernateConnection );
			hibernateConnection.commitTransacao();

		} catch ( ApplicationException e ) {
			hibernateConnection.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernateConnection.rollbackTransacao();
			throw new ApplicationException( "Erro desconhecido ao inserir...", e );
		}
	}

	@Override
	public void alterar( NotaPO po ) throws ApplicationException {
		HibernateConnection hibernateConnection = new HibernateConnection();
		try {
			if ( po == null ) {
				throw new ApplicationException( "Parâmetro não pode ser NULO!" );
			}

			if ( po.getNota() == null ) {
				throw new ApplicationException( "Nota não pode ser NULA!" );
			}

			if ( po.getMateria().getNome() == null && po.getMateria().getId() == null ) {
				throw new ApplicationException( "Matéria não Informada!" );
			} else {
				//FOI FEITO ISSO, POIS PARA INSERIR MATERIA PRECISO ANTES FILTRAR.
				po.setMateria( (MateriaPO) hibernateConnection.filtrarPorId( MateriaPO.class, po.getMateria().getId() ) );
			}

			if ( po.getData() == null ) {
				throw new ApplicationException( "Data não pode ser NULO!" );
			}

			hibernateConnection.iniciarTransacao();
			DAO.alterar( po, hibernateConnection );
			hibernateConnection.commitTransacao();

		} catch ( ApplicationException e ) {
			hibernateConnection.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernateConnection.rollbackTransacao();
			throw new ApplicationException( "Erro desconhecido ao  alterar...", e );
		}
	}

	@Override
	public void excluir( NotaPO po ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {

			if ( po == null ) {
				throw new ApplicationException( "Parâmetro não pode ser NULO!" );
			}
			if ( po.getId() == null ) {
				throw new ApplicationException( "Id não pode ser NULO!" );
			}
			hibernate.iniciarTransacao();
			DAO.excluir( po, hibernate );
			hibernate.commitTransacao();
		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			throw new ApplicationException( "Erro desconhecido ao excluir...", e );
		}
	}

	@Override
	public ArrayList< NotaPO > filtrar( NotaPO po ) throws ApplicationException {
		try {
			if ( po == null ) {
				throw new ApplicationException( "Parâmetro não pode ser NULO!" );
			}

			List< NotaPO > encontrados = DAO.filtrar( po );
			return (ArrayList< NotaPO >) encontrados;

		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido ao filtrar...", e );
		}
	}

	@Override
	public ArrayList< NotaPO > filtrarTodos() throws ApplicationException {

		try {
			List< NotaPO > encontrados = DAO.filtrarTodos();
			return (ArrayList< NotaPO >) encontrados;

		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido ao filtrar todos...", e );
		}
	}

	@Override
	public NotaPO filtrarPorId( Long id ) throws ApplicationException {
		try {
			if ( id == null ) {
				throw new ApplicationException( "Parametro não pode ser NULO!" );
			}

			NotaPO encontrados = DAO.filtrarPorId( id );
			return encontrados;

		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido ao filtrar por ID...", e );
		}
	}

	/**
	 * 
	 * Método responsável por FILTRAR MATERIA POR NOME, POIS PRECISO DELE NO AUTO COMPLETE
	 *
	 * @param nome
	 * @return
	 * @throws ApplicationException
	 *
	 * @author André Duarte <andre.silva141@fatec.sp.gov.br>
	 * @since 27 de jun de 2017 17:30:42
	 * @version 1.0
	 */
	public ArrayList< MateriaPO > filtrarMateriaPorNome( String nome ) throws ApplicationException {
		try {

			List< MateriaPO > encontrados = DAO.filtrarMateriaPorNome( nome );

			return (ArrayList< MateriaPO >) encontrados;

		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido ao filtrar...", e );
		}
	}
}
