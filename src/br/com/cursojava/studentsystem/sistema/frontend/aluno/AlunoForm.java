package br.com.cursojava.studentsystem.sistema.frontend.aluno;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import br.com.cursojava.studentsystem.sistema.backend.aluno.model.AlunoPO;

/**
 * 
 * Classe responsável por gerenciar todos os campos da tela.
 * Esta classe terá um atributo para cada campo existente na tela.
 * Quando o formulário do JSP é SUBMETIDO, o request pega os dados dos
 * campos, envia para o struts que por sua vez preenche a esta classe FORM.
 * 
 * PS: Tudo que quiser fazer na tela em relação a valores de campos, deve-se fazer aqui
 * nesta classe, pois no momento da montagem da tela, os campos respeitam os valores contidos
 * nos atributos.
 * 
 * @author André Duarte <andre.silva141@fatec.sp.gov.br>
 * @since 19 de abr de 2017 20:04:00
 * @version 1.0
 */
public class AlunoForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7195368720281200101L;
	private String id;
	private String nome;
	private String cpf;
	private String altura;
	private String ra;
	private String dataNascimento;
	private String idTurma;
	private String peso;
	private String sexo;
	private String nomeTurma;

	//CAMPOS DO ENDEREÇO

	private String idEndereco;
	private String logradouro;
	private String numero;
	private String bairro;
	private String cidade;
	private String cep;
	private String uf;

	private ArrayList< AlunoPO > alunos = new ArrayList< AlunoPO >();

	public ArrayList< AlunoPO > getAlunos() {
		return alunos;
	}

	public void setAlunos( ArrayList< AlunoPO > alunos ) {
		this.alunos = alunos;
	}

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf( String cpf ) {
		this.cpf = cpf;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura( String altura ) {
		this.altura = altura;
	}

	public String getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco( String idEndereco ) {
		this.idEndereco = idEndereco;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro( String logradouro ) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero( String numero ) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro( String bairro ) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade( String cidade ) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep( String cep ) {
		this.cep = cep;
	}

	public String getUf() {
		return uf;
	}

	public void setUf( String uf ) {
		this.uf = uf;
	}

	public String getRa() {
		return ra;
	}

	public void setRa( String ra ) {
		this.ra = ra;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento( String dataNascimento ) {
		this.dataNascimento = dataNascimento;
	}

	public String getIdTurma() {
		return idTurma;
	}

	public void setIdTurma( String idTurma ) {
		this.idTurma = idTurma;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso( String peso ) {
		this.peso = peso;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo( String sexo ) {
		this.sexo = sexo;
	}

	public String getNomeTurma() {
		return nomeTurma;
	}

	public void setNomeTurma( String nomeTurma ) {
		this.nomeTurma = nomeTurma;
	}

	public void limparCampos() {
		setId( null );
		setNome( null );
		setDataNascimento( null );
		setAltura( null );
		setCpf( null );
		setPeso( null );
		setSexo( null );
		setIdTurma( null );
		setRa( null );
		setNomeTurma( null );

		setIdEndereco( null );
		setLogradouro( null );
		setNumero( null );
		setBairro( null );
		setCidade( null );
		setCep( null );
		setUf( null );
	}
}
