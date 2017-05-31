package br.com.cursojava.studentsystem.sistema.backend.aluno.controller;

import java.util.ArrayList;

import br.com.cursojava.studentsystem.exception.ApplicationException;
import br.com.cursojava.studentsystem.interfaces.Crud;
import br.com.cursojava.studentsystem.sistema.backend.aluno.model.AlunoPO;
import br.com.cursojava.studentsystem.sistema.backend.aluno.model.AlunoSERVICE;

public final class AlunoFACADE implements Crud< AlunoPO >{
	private final Crud< AlunoPO > SERVICE;

	//CONSTRUTOR RESPONSÁVEL POR INICIALIZAR O ATRIBUTO SERVICE.
	public AlunoFACADE(){
		SERVICE = new AlunoSERVICE();
	}

	@Override
	public void inserir( AlunoPO po ) throws ApplicationException {
		SERVICE.inserir( po );
	}

	@Override
	public void alterar( AlunoPO po ) throws ApplicationException {
		SERVICE.alterar( po );
	}

	@Override
	public void excluir( AlunoPO po ) throws ApplicationException {
		SERVICE.excluir( po );
	}

	@Override
	public ArrayList< AlunoPO > filtrar( AlunoPO po ) throws ApplicationException {
		return SERVICE.filtrar( po );
	}

	@Override
	public ArrayList< AlunoPO > filtrarTodos() throws ApplicationException {
		return SERVICE.filtrarTodos();
	}

	@Override
	public AlunoPO filtrarPorId( Long id ) throws ApplicationException {
		return SERVICE.filtrarPorId( id );
	}

}
