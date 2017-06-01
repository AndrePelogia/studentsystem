package br.com.cursojava.studentsystem.sistema.frontend.turma;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import br.com.cursojava.studentsystem.sistema.backend.turma.model.TurmaPO;

public class TurmaForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String nome;
	private String dataInicio;
	private String dataTermino;

	/*UTILIZO QUANDO FOR SELECIONAR UM OBJETO NA TELA OS CAMPOS VÃO ESTAR PREENCHIDOS*/
	private ArrayList< TurmaPO > turmas = new ArrayList< TurmaPO >();

	/*GETTERS AND SETTERS*/
	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio( String dataInicio ) {
		this.dataInicio = dataInicio;
	}

	public String getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino( String dataTermino ) {
		this.dataTermino = dataTermino;
	}

	public ArrayList< TurmaPO > getTurmas() {
		return turmas;
	}

	public void setTurmas( ArrayList< TurmaPO > turmas ) {
		this.turmas = turmas;
	}

	/*MÉTODO QUE IRÁ LIMPAR OS CAMPOS DA MINHA TELA QUANDO FOR INVOCADO*/
	public void limparCampos() {
		setId( null );
		setNome( null );
		setDataInicio( null );
		setDataTermino( null );
	}

}
