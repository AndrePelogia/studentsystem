package br.com.cursojava.studentsystem.sistema.backend.aluno.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ForeignKey;

import br.com.cursojava.studentsystem.abstracts.AbstractPO;
import br.com.cursojava.studentsystem.interfaces.Crud;
import br.com.cursojava.studentsystem.sistema.backend.endereco.model.EnderecoPO;
import br.com.cursojava.studentsystem.utilidades.Utilidades;

@Entity
@Table( name = "alunos", schema = Crud.SCHEMA, uniqueConstraints = @UniqueConstraint( columnNames = { "nome", "cpf", "ra" } ) )
public final class AlunoPO extends AbstractPO< AlunoPO >{

	@Column( name = "nome", length = 80, nullable = false )
	private String nome;

	@Column( name = "cpf", length = 14, nullable = false )
	private String cpf;

	@Column( name = "altura", precision = 3, scale = 2, nullable = true )
	private BigDecimal altura;

	/*@Lob //permite que o length aumente o tamanho, pois o máx. é 255.
	@Column( name = "endereco", length = 500, nullable = false )
	private String endereco;*/

	@Column( name = "ra", length = 8, nullable = false )
	private String ra;

	@Temporal( TemporalType.DATE )
	@Column( name = "dataNascimento", length = 10, nullable = false )
	private Date dataNascimento;

	@Column( name = "turma", length = 50, nullable = false )
	private String turma;

	@Column( name = "peso", precision = 5, scale = 2, nullable = true )
	private BigDecimal peso;

	@Column( name = "sexo", length = 9, nullable = false )
	private String sexo;

	/**
	 * 
	 * Atributo de relacionamento entre ALUNO e ENDEREÇO
	 * Neste caso está sendo configurado um AGREGAÇÃO POR COMPOSIÇÃO
	 */
	//SEMPRE QUE TRAZER O ALUNO ELE TRAS O ENDEREÇO, TUDO QUE FAZ NO ALUNO ELE REFLETO NO ENDEREÇO
	@OneToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false, orphanRemoval = true )
	@JoinColumn( name = "idEndereco" )
	@ForeignKey( name = "fk_aluno_endereco" )
	private EnderecoPO endereco = new EnderecoPO();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( cpf == null ) ? 0 : cpf.hashCode() );
		result = prime * result + ( ( nome == null ) ? 0 : nome.hashCode() );
		result = prime * result + ( ( ra == null ) ? 0 : ra.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}
		if ( obj == null ) {
			return false;
		}
		if ( !( obj instanceof AlunoPO ) ) {
			return false;
		}
		AlunoPO other = (AlunoPO) obj;
		if ( cpf == null ) {
			if ( other.cpf != null ) {
				return false;
			}
		} else if ( !cpf.equals( other.cpf ) ) {
			return false;
		}
		if ( nome == null ) {
			if ( other.nome != null ) {
				return false;
			}
		} else if ( !nome.equals( other.nome ) ) {
			return false;
		}
		if ( ra == null ) {
			if ( other.ra != null ) {
				return false;
			}
		} else if ( !ra.equals( other.ra ) ) {
			return false;
		}
		return true;
	}

	public AlunoPO( String nome, String cpf, String turma ){
		setNome( nome );
		setCpf( cpf );
		setTurma( turma );
	}

	//exigencia do hibernate
	public AlunoPO(){

	}

	public AlunoPO( Long id ){
		setId( id );

	}

	public String getNome() {
		return nome;
	}

	public void setNome( String nome ) {
		this.nome = Utilidades.normalizeString( nome );
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf( String cpf ) {
		this.cpf = Utilidades.normalizeString( cpf );
	}

	public BigDecimal getAltura() {
		return altura;
	}

	public void setAltura( BigDecimal altura ) {
		this.altura = altura;
	}

	/*public String getEndereco() {
		return endereco;
	}*/

	/*//foi feito isso para encapsular o endereço, assim o usuário deverá preencher endereço do jeito que está no metodo.
	public void setEndereco( String logradouro, String numero, String bairro, String cidade, String cep, String uf ) {
		endereco = ( ( Utilidades.normalizeString( logradouro ) ) + ", " + ( Utilidades.normalizeString( numero ) ) + " " + ( Utilidades.normalizeString( bairro ) ) + " " + ( Utilidades.normalizeString( cep ) ) + " " + ( Utilidades.normalizeString( cidade ) ) + "/" + ( Utilidades.normalizeString( uf ) ) );
		endereco = endereco.trim().replace( ",", "" ).trim();
	}*/

	public String getRa() {
		return ra;
	}

	public void setRa( String ra ) {
		this.ra = Utilidades.normalizeString( ra );
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento( Date dataNascimento ) {
		this.dataNascimento = dataNascimento;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma( String turma ) {
		this.turma = Utilidades.normalizeString( turma );
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso( BigDecimal peso ) {
		this.peso = peso;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo( String sexo ) {
		this.sexo = Utilidades.normalizeString( sexo );
	}

	public EnderecoPO getEndereco() {
		return endereco;
	}

	public void setEndereco( EnderecoPO endereco ) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "AlunoPO [nome=" );
		builder.append( nome );
		builder.append( ", cpf=" );
		builder.append( cpf );
		builder.append( ", altura=" );
		builder.append( altura );
		builder.append( ", endereco=" );
		builder.append( endereco );
		builder.append( ", ra=" );
		builder.append( ra );
		builder.append( ", dataNascimento=" );
		builder.append( dataNascimento );
		builder.append( ", turma=" );
		builder.append( turma );
		builder.append( ", peso=" );
		builder.append( peso );
		builder.append( ", sexo=" );
		builder.append( sexo );
		builder.append( ", getId()=" );
		builder.append( getId() );
		builder.append( "]" );
		return builder.toString();
	}

	/**
	 * Método responsável por conter a regra de comparação da Classe AlunoPO.
	 * Este método é usado sempre que quisermos ordernar uma lista de AlunoPO
	 * ou comparar uma AlunoPO com outro AlunoPO.
	 * Os retornos deste método significam o seguinte:
	 * # >1 - Quando o Objeto principal for MAIOR que o do parametro.
	 * # 0 - Quando o Objeto principal for IGUAL ao do parametro.
	 * # <1 - Quando o Objeto principal for MENOR que o do parametro.
	 * Polimorfico
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo( AlunoPO o ) {
		return this.getNome().compareToIgnoreCase( o.getNome() );
	}

}
