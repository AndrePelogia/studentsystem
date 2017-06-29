package br.com.cursojava.studentsystem.sistema.backend.nota.controller;

import java.util.ArrayList;

import br.com.cursojava.studentsystem.exception.ApplicationException;
import br.com.cursojava.studentsystem.interfaces.Crud;
import br.com.cursojava.studentsystem.sistema.backend.materia.model.MateriaPO;
import br.com.cursojava.studentsystem.sistema.backend.nota.model.NotaPO;
import br.com.cursojava.studentsystem.sistema.backend.nota.model.NotaSERVICE;

public class NotaFACADE implements Crud< NotaPO >{

	/**
	 * PARA EU FAZER O AUTOCOMPLETE DE MATERIA, TIVE QUE MUDAR A CONSTANTE SERVICE,
	 * POIS ANTES ELE ERA DO TIPO CRUD<NOTAPO>, AGORA ELE É DO TIPO SERVICE MESMO.
	 */
	private final NotaSERVICE SERVICE;

	public NotaFACADE(){
		SERVICE = new NotaSERVICE();
	}

	@Override
	public void inserir( NotaPO po ) throws ApplicationException {
		SERVICE.inserir( po );
	}

	@Override
	public void alterar( NotaPO po ) throws ApplicationException {
		SERVICE.alterar( po );
	}

	@Override
	public void excluir( NotaPO po ) throws ApplicationException {
		SERVICE.excluir( po );
	}

	@Override
	public ArrayList< NotaPO > filtrar( NotaPO po ) throws ApplicationException {
		return SERVICE.filtrar( po );
	}

	@Override
	public ArrayList< NotaPO > filtrarTodos() throws ApplicationException {
		return SERVICE.filtrarTodos();
	}

	@Override
	public NotaPO filtrarPorId( Long id ) throws ApplicationException {
		return SERVICE.filtrarPorId( id );
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
		return SERVICE.filtrarMateriaPorNome( nome );
	}

}
