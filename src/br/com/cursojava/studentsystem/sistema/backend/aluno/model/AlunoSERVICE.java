package br.com.cursojava.studentsystem.sistema.backend.aluno.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.cursojava.studentsystem.connection.HibernateConnection;
import br.com.cursojava.studentsystem.exception.ApplicationException;
import br.com.cursojava.studentsystem.interfaces.Crud;
import br.com.cursojava.studentsystem.sistema.backend.aluno.dao.AlunoDAO;
import br.com.cursojava.studentsystem.utilidades.Utilidades;

public final class AlunoSERVICE implements Crud< AlunoPO >{
	//GARANTE A APLICAÇÃO DA ASSOCIAÇÃO ENTRE O SERVICE E O DAO
	private final AlunoDAO DAO;

	//CONSTRUTOR RESPONSÁVEL POR INICIALIZAR O ATRIBUTO DAO.
	public AlunoSERVICE(){
		DAO = new AlunoDAO();
	}

	@Override
	public void inserir( AlunoPO po ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();

		try {

			if ( po == null ) {
				throw new ApplicationException( "Parâmetro não pode ser NULO!" );
			}

			if ( !Utilidades.isCpfValido( po.getCpf() ) ) {
				throw new ApplicationException( "CPF inválido - " + po.getCpf() );
			}
			//APLICANDO AS VALIDAÇÕES UTILIZANDO IF NEGATIVO 
			//before e after metodos para comparar DATAS!!!

			if ( po.getDataNascimento() != null && new Date().before( po.getDataNascimento() ) ) {
				// Entra aqui caso a data de nascimento esteja inválida
				throw new ApplicationException( "Data de nascimento inválida - " + po.getDataNascimento() );
			}
			/*1º INICIAR TRANSAÇÃO*/
			hibernate.iniciarTransacao();

			/*2º FAZER O QUE QUERO*/
			DAO.inserir( po, hibernate );

			/*3º CONFIRMAR A TRANSAÇÃO, CASO DE CERTO*/
			hibernate.commitTransacao();

		} catch ( ApplicationException e ) {
			/*4º DESFAZER A TRANSAÇÃO, CASO DE ERRADO*/
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			throw new ApplicationException( "Erro desconhecido ao inserir...", e );
		}
	}

	@Override
	public void alterar( AlunoPO po ) throws ApplicationException {
		HibernateConnection hibernate = new HibernateConnection();
		try {

			if ( po == null ) {
				throw new ApplicationException( "Parâmetro não pode ser NULO!" );
			}

			if ( !Utilidades.isCpfValido( po.getCpf() ) ) {
				throw new ApplicationException( "CPF inválido - " + po.getCpf() );
			}
			//APLICANDO AS VALIDAÇÕES UTILIZANDO IF NEGATIVO 
			//before e after metodos para comparar DATAS!!!

			if ( po.getDataNascimento() != null && new Date().before( po.getDataNascimento() ) ) {
				// Entra aqui caso a data de nascimento esteja inválida
				throw new ApplicationException( "Data de nascimento inválida - " + po.getDataNascimento() );
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
	public void excluir( AlunoPO po ) throws ApplicationException {

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
	public ArrayList< AlunoPO > filtrar( AlunoPO po ) throws ApplicationException {

		try {

			if ( po == null ) {
				throw new ApplicationException( "Parâmetro não pode ser NULO!" );
			}

			List< AlunoPO > encontrados = DAO.filtrar( po );

			/*RETORNO DO MEU FILTRAR*/
			return (ArrayList< AlunoPO >) encontrados;
		} catch ( ApplicationException e ) {

			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido ao filtrar...", e );
		}

		//DAO = new AlunoDAO();

	}

	@Override
	public ArrayList< AlunoPO > filtrarTodos() throws ApplicationException {

		try {

			List< AlunoPO > encontrados = DAO.filtrarTodos();

			return (ArrayList< AlunoPO >) encontrados;

		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido ao filtrar todos...", e );
		}
		//dao = new AlunoDAO();

	}

	@Override
	public AlunoPO filtrarPorId( Long id ) throws ApplicationException {

		try {

			if ( id == null ) {
				throw new ApplicationException( "Parâmetro não pode ser NULO!" );
			}

			AlunoPO encontrados = DAO.filtrarPorId( id );

			return encontrados;

		} catch ( ApplicationException e ) {

			throw e;
		} catch ( Exception e ) {

			throw new ApplicationException( "Erro desconhecido ao filtrar por ID...", e );
		}

	}
}
