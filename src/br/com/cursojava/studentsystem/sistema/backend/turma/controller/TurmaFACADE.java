package br.com.cursojava.studentsystem.sistema.backend.turma.controller;

import java.util.ArrayList;

import br.com.cursojava.studentsystem.exception.ApplicationException;
import br.com.cursojava.studentsystem.interfaces.Crud;
import br.com.cursojava.studentsystem.sistema.backend.turma.model.TurmaPO;
import br.com.cursojava.studentsystem.sistema.backend.turma.model.TurmaSERVICE;

public class TurmaFACADE implements Crud< TurmaPO >{

	private final Crud< TurmaPO > SERVICE;

	public TurmaFACADE(){
		SERVICE = new TurmaSERVICE();
	}

	@Override
	public void inserir( TurmaPO po ) throws ApplicationException {
		SERVICE.inserir( po );
	}

	@Override
	public void alterar( TurmaPO po ) throws ApplicationException {
		SERVICE.alterar( po );
	}

	@Override
	public void excluir( TurmaPO po ) throws ApplicationException {
		SERVICE.excluir( po );
	}

	@Override
	public ArrayList< TurmaPO > filtrar( TurmaPO po ) throws ApplicationException {
		return SERVICE.filtrar( po );
	}

	@Override
	public ArrayList< TurmaPO > filtrarTodos() throws ApplicationException {
		return SERVICE.filtrarTodos();
	}

	@Override
	public TurmaPO filtrarPorId( Long id ) throws ApplicationException {
		return SERVICE.filtrarPorId( id );
	}

}
