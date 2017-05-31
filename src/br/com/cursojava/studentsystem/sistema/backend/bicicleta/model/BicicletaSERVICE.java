package br.com.cursojava.studentsystem.sistema.backend.bicicleta.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.cursojava.studentsystem.connection.HibernateConnection;
import br.com.cursojava.studentsystem.exception.ApplicationException;
import br.com.cursojava.studentsystem.interfaces.Crud;
import br.com.cursojava.studentsystem.sistema.backend.bicicleta.dao.BicicletaDAO;

public final class BicicletaSERVICE implements Crud< BicicletaPO >{

	//GARANTE A APLICA��O DA ASSOCIA��O ENTRE O SERVICE E O DAO
	private final BicicletaDAO DAO;

	//CONSTRUTOR RESPONS�VEL POR INICIALIZAR O ATRIBUTO DAO.
	public BicicletaSERVICE(){
		DAO = new BicicletaDAO();
	}

	@Override
	public void inserir( BicicletaPO po ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();

		try {

			if ( po == null ) {
				throw new ApplicationException( "Par�metro n�o pode ser NULO!" );
			}

			if ( po.getMarca() == null ) {
				throw new ApplicationException( "Marca n�o pode ser NULA!" );
			}
			//APLICANDO AS VALIDA��ES UTILIZANDO IF NEGATIVO 
			//before e after metodos para comparar DATAS!!!

			if ( po.getDataFabricacao() != null && new Date().before( po.getDataFabricacao() ) ) {
				// Entra aqui caso a data de nascimento esteja inv�lida
				throw new ApplicationException( "Data de fabrica��o inv�lida - " + po.getDataFabricacao() );
			}
			/*1� INICIAR TRANSA��O*/
			hibernate.iniciarTransacao();

			/*2� FAZER O QUE QUERO*/
			DAO.inserir( po, hibernate );

			/*3� CONFIRMAR A TRANSA��O, CASO DE CERTO*/
			hibernate.commitTransacao();

		} catch ( ApplicationException e ) {
			/*4� DESFAZER A TRANSA��O, CASO DE ERRADO*/
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			throw new ApplicationException( "Erro desconhecido ao inserir...", e );
		}

	}

	@Override
	public void alterar( BicicletaPO po ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();
		try {

			if ( po == null ) {
				throw new ApplicationException( "Par�metro n�o pode ser NULO!" );
			}

			if ( po.getMarca() == null ) {
				throw new ApplicationException( "Marca n�o pode ser NULA!" );
			}
			//APLICANDO AS VALIDA��ES UTILIZANDO IF NEGATIVO 
			//before e after metodos para comparar DATAS!!!

			if ( po.getDataFabricacao() != null && new Date().before( po.getDataFabricacao() ) ) {
				// Entra aqui caso a data de nascimento esteja inv�lida
				throw new ApplicationException( "Data de fabrica��o inv�lida - " + po.getDataFabricacao() );
			}
			hibernate.iniciarTransacao();
			DAO.alterar( po, hibernate );
			hibernate.commitTransacao();
		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();

			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();

			throw new ApplicationException( "Erro desconhecido ao alterar...", e );
		}
	}

	@Override
	public void excluir( BicicletaPO po ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();

		try {

			if ( po == null ) {
				throw new ApplicationException( "Par�metro n�o pode ser NULO!" );
			}
			if ( po.getId() == null ) {
				throw new ApplicationException( "Id n�o pode ser NULO!" );
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
	public ArrayList< BicicletaPO > filtrar( BicicletaPO po ) throws ApplicationException {

		try {

			if ( po == null ) {
				throw new ApplicationException( "Par�metro n�o pode ser NULO!" );
			}

			List< BicicletaPO > encontrados = DAO.filtrar( po );

			/*RETORNO DO MEU FILTRAR*/
			return (ArrayList< BicicletaPO >) encontrados;

		} catch ( ApplicationException e ) {

			throw e;
		} catch ( Exception e ) {

			throw new ApplicationException( "Erro desconhecido ao filtrar...", e );
		}

		//DAO = new AlunoDAO();

	}

	@Override
	public ArrayList< BicicletaPO > filtrarTodos() throws ApplicationException {

		try {

			List< BicicletaPO > encontrados = DAO.filtrarTodos();

			return (ArrayList< BicicletaPO >) encontrados;

		} catch ( ApplicationException e ) {

			throw e;
		} catch ( Exception e ) {

			throw new ApplicationException( "Erro desconhecido ao filtrar todos...", e );
		}

	}

	@Override
	public BicicletaPO filtrarPorId( Long id ) throws ApplicationException {

		try {

			if ( id == null ) {
				throw new ApplicationException( "Par�metro n�o pode ser NULO!" );
			}

			BicicletaPO encontrados = DAO.filtrarPorId( id );

			return encontrados;

		} catch ( ApplicationException e ) {

			throw e;
		} catch ( Exception e ) {

			throw new ApplicationException( "Erro desconhecido ao filtrar por ID...", e );
		}

	}

}
