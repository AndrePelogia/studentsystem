package br.com.cursojava.studentsystem.sistema.frontend.bicicleta;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.struts.action.ActionForm;
import org.apache.struts.util.LabelValueBean;

import br.com.cursojava.studentsystem.exception.ApplicationException;
import br.com.cursojava.studentsystem.sistema.backend.aluno.controller.AlunoFACADE;
import br.com.cursojava.studentsystem.sistema.backend.aluno.model.AlunoPO;
import br.com.cursojava.studentsystem.sistema.backend.bicicleta.model.BicicletaPO;

public class BicicletaForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2063819471586860993L;
	private String id;
	private String cor;
	private String marca;
	private String modalidade;
	private String preco;
	private String marcha;
	private String dataFabricacao;
	private String material;
	private String idAluno;

	/*UTILIZO QUANDO FOR SELECIONAR UM OBJETO NA TELA OS CAMPOS VÃO ESTAR PREENCHIDOS*/
	private ArrayList< BicicletaPO > bicicletas = new ArrayList< BicicletaPO >();

	/*GETTERS AND SETTERS*/

	public String getCor() {
		return cor;
	}

	public String getIdAluno() {
		return idAluno;
	}

	public void setIdAluno( String idAluno ) {
		this.idAluno = idAluno;
	}

	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public ArrayList< BicicletaPO > getBicicletas() {
		return bicicletas;
	}

	public void setBicicletas( ArrayList< BicicletaPO > bicicletas ) {
		this.bicicletas = bicicletas;
	}

	public void setCor( String cor ) {
		this.cor = cor;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca( String marca ) {
		this.marca = marca;
	}

	public String getModalidade() {
		return modalidade;
	}

	public void setModalidade( String modalidade ) {
		this.modalidade = modalidade;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco( String preco ) {
		this.preco = preco;
	}

	public String getMarcha() {
		return marcha;
	}

	public void setMarcha( String marcha ) {
		this.marcha = marcha;
	}

	public String getDataFabricacao() {
		return dataFabricacao;
	}

	public void setDataFabricacao( String dataFabricacao ) {
		this.dataFabricacao = dataFabricacao;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial( String material ) {
		this.material = material;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	//PRECISO DESTE MÉTODO NO FORM PARA PODER MONTAR O COMBO DE ALUNO NA TELA DE CADASTRO DE BICICLETAS

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

	/*MÉTODO QUE IRÁ LIMPAR OS CAMPOS DA MINHA TELA QUANDO FOR INVOCADO*/
	public void limparCampos() {
		setId( null );
		setCor( null );
		setDataFabricacao( null );
		setMarca( null );
		setMarcha( null );
		setMaterial( null );
		setModalidade( null );
		setPreco( null );
		setIdAluno( null );
	}

}
