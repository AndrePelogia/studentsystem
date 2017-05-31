package br.com.cursojava.studentsystem.sistema.backend.turma.model;

import java.util.ArrayList;
import java.util.List;

import br.com.cursojava.studentsystem.connection.HibernateConnection;
import br.com.cursojava.studentsystem.exception.ApplicationException;
import br.com.cursojava.studentsystem.interfaces.Crud;
import br.com.cursojava.studentsystem.sistema.backend.turma.dao.TurmaDAO;

public class TurmaSERVICE implements Crud< TurmaPO >{

	private final TurmaDAO DAO;

	public TurmaSERVICE(){
		DAO = new TurmaDAO();
	}

	@Override
	public void inserir( TurmaPO po ) throws ApplicationException {

		HibernateConnection hibernate = new HibernateConnection();

		try {

			if ( po == null ) {
				throw new ApplicationException( "Par�metro n�o pode ser NULO!" );
			}

			//APLICANDO AS VALIDA��ES UTILIZANDO IF NEGATIVO 
			//before e after metodos para comparar DATAS!!!

			if ( po.getDataInicio() != null && po.getDataInicio().after( po.getDataTermino() ) ) {
				// Entra aqui caso a data de nascimento esteja inv�lida
				throw new ApplicationException( "Data de in�cio, n�o pode ser maior que de termino - " + po.getDataInicio() );
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
	public void alterar( TurmaPO po ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();
		try {

			if ( po == null ) {
				throw new ApplicationException( "Par�metro n�o pode ser NULO!" );
			}

			if ( po.getDataInicio() != null && po.getDataInicio().after( po.getDataTermino() ) ) {
				// Entra aqui caso a data de nascimento esteja inv�lida
				throw new ApplicationException( "Data de in�cio, n�o pode ser maior que de termino - " + po.getDataInicio() );
			}

			hibernate.iniciarTransacao();
			DAO.alterar( po, hibernate );
			hibernate.commitTransacao();

		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			throw new ApplicationException( "Erro desconhecido ao inserir...", e );
		}
	}

	@Override
	public void excluir( TurmaPO po ) throws ApplicationException {

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
	public ArrayList< TurmaPO > filtrar( TurmaPO po ) throws ApplicationException {

		try {

			if ( po == null ) {
				throw new ApplicationException( "Par�metro n�o pode ser NULO!" );
			}

			List< TurmaPO > encontrados = DAO.filtrar( po );

			/*RETORNO DO MEU FILTRAR*/
			return (ArrayList< TurmaPO >) encontrados;
		} catch ( ApplicationException e ) {

			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido ao filtrar...", e );
		}
	}

	@Override
	public ArrayList< TurmaPO > filtrarTodos() throws ApplicationException {

		try {

			List< TurmaPO > encontrados = DAO.filtrarTodos();

			return (ArrayList< TurmaPO >) encontrados;

		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido ao filtrar todos...", e );
		}
		//dao = new AlunoDAO();

	}

	@Override
	public TurmaPO filtrarPorId( Long id ) throws ApplicationException {

		try {

			if ( id == null ) {
				throw new ApplicationException( "Par�metro n�o pode ser NULO!" );
			}

			TurmaPO encontrados = DAO.filtrarPorId( id );

			return encontrados;

		} catch ( ApplicationException e ) {

			throw e;
		} catch ( Exception e ) {

			throw new ApplicationException( "Erro desconhecido ao filtrar por ID...", e );
		}

	}

}
