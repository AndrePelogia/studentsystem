package br.com.cursojava.studentsystem.sistema.backend.bicicleta.controller;

import java.util.ArrayList;

import br.com.cursojava.studentsystem.exception.ApplicationException;
import br.com.cursojava.studentsystem.interfaces.Crud;
import br.com.cursojava.studentsystem.sistema.backend.bicicleta.model.BicicletaPO;
import br.com.cursojava.studentsystem.sistema.backend.bicicleta.model.BicicletaSERVICE;

public final class BicicletaFACADE{

	private final Crud< BicicletaPO > SERVICE;

	//CONSTRUTOR RESPONSÁVEL POR INICIALIZAR O ATRIBUTO SERVICE.
	public BicicletaFACADE(){
		SERVICE = new BicicletaSERVICE();
	}

	public void inserir( BicicletaPO po ) throws ApplicationException {
		SERVICE.inserir( po );
	}

	public void alterar( BicicletaPO po ) throws ApplicationException {
		SERVICE.alterar( po );
	}

	public void excluir( BicicletaPO po ) throws ApplicationException {
		SERVICE.excluir( po );
	}

	public ArrayList< BicicletaPO > filtrar( BicicletaPO po ) throws ApplicationException {
		return SERVICE.filtrar( po );
	}

	public ArrayList< BicicletaPO > filtrarTodos() throws ApplicationException {
		return SERVICE.filtrarTodos();
	}

	public BicicletaPO filtrarPorId( Long id ) throws ApplicationException {
		return SERVICE.filtrarPorId( id );
	}
}
