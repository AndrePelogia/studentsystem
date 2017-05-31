package br.com.cursojava.studentsystem.interfaces;

import java.util.ArrayList;

import br.com.cursojava.studentsystem.exception.ApplicationException;

public interface Crud< T > {
	/*	Todo ATRIBUTO É PUBLIC STATIC FINAL 
	*/ String SCHEMA = "student_system_t31n";

	void inserir( T po ) throws ApplicationException;

	void alterar( T po ) throws ApplicationException;

	void excluir( T po ) throws ApplicationException;

	ArrayList< T > filtrar( T po ) throws ApplicationException;

	ArrayList< T > filtrarTodos() throws ApplicationException;

	T filtrarPorId( Long id ) throws ApplicationException;
}
