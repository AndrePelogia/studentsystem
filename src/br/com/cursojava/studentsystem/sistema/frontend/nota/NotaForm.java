package br.com.cursojava.studentsystem.sistema.frontend.nota;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.struts.action.ActionForm;
import org.apache.struts.util.LabelValueBean;

import br.com.cursojava.studentsystem.exception.ApplicationException;
import br.com.cursojava.studentsystem.sistema.backend.aluno.controller.AlunoFACADE;
import br.com.cursojava.studentsystem.sistema.backend.aluno.model.AlunoPO;
import br.com.cursojava.studentsystem.sistema.backend.nota.model.NotaPO;

public class NotaForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3060158522841921771L;
	private String id;
	private String nota;
	private String observacao;
	private String data;
	//MATERIA
	private String idMateria;
	private String nome;
	private String professor;
	//ALUNO
	private String idAluno;

	public String getIdAluno() {
		return idAluno;
	}

	public void setIdAluno( String idAluno ) {
		this.idAluno = idAluno;
	}

	private ArrayList< NotaPO > notas = new ArrayList< NotaPO >();

	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public String getNota() {
		return nota;
	}

	public void setNota( String nota ) {
		this.nota = nota;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao( String observacao ) {
		this.observacao = observacao;
	}

	public String getData() {
		return data;
	}

	public void setData( String data ) {
		this.data = data;
	}

	public String getIdMateria() {
		return idMateria;
	}

	public void setIdMateria( String idMateria ) {
		this.idMateria = idMateria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor( String professor ) {
		this.professor = professor;
	}

	public ArrayList< NotaPO > getNotas() {
		return notas;
	}

	public void setNotas( ArrayList< NotaPO > notas ) {
		this.notas = notas;
	}

	/*MÉTODO QUE IRÁ LIMPAR OS CAMPOS DA MINHA TELA QUANDO FOR INVOCADO*/
	public void limparCampos() {
		setId( null );
		setNota( null );
		setData( null );
		setIdMateria( null );
		setNome( null );
		setObservacao( null );
		setProfessor( null );
		setIdAluno( null );
	}

	//PRECISO DESTE MÉTODO NO FORM PARA PODER MONTAR O COMBO DE ALUNO NA TELA DE CADASTRO DE NOTAS

	public ArrayList< LabelValueBean > getComboAlunos() {

		ArrayList< LabelValueBean > lista = new ArrayList< LabelValueBean >();

		try {
			AlunoFACADE facade = new AlunoFACADE();

			ArrayList< AlunoPO > encontrados = facade.filtrarTodos();

			Iterator< AlunoPO > setIter = encontrados.iterator();

			LabelValueBean lvb = new LabelValueBean();
			lvb.setLabel( "Selecione um aluno" );
			lvb.setValue( null );
			lista.add( lvb );

			while ( setIter.hasNext() ) {
				AlunoPO aluno = setIter.next();

				lvb = new LabelValueBean();

				lvb.setLabel( aluno.getId() + " - " + aluno.getNome() );

				lvb.setValue( aluno.getId().toString() );

				lista.add( lvb );
			}
		} catch ( ApplicationException e ) {
			e.printStackTrace();

		} catch ( Exception e ) {
			e.printStackTrace();
		}

		return lista;
	}
}
