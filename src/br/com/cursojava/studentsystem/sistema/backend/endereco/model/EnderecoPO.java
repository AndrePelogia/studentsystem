package br.com.cursojava.studentsystem.sistema.backend.endereco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.cursojava.studentsystem.abstracts.AbstractPO;
import br.com.cursojava.studentsystem.interfaces.Crud;
import br.com.cursojava.studentsystem.utilidades.Utilidades;

@Entity
@Table( name = "enderecos", schema = Crud.SCHEMA, uniqueConstraints = @UniqueConstraint( columnNames = { "logradouro", "numero", "bairro", "cidade", "cep", "uf" } ) )
public final class EnderecoPO extends AbstractPO< EnderecoPO >{

	@Column( name = "logradouro", length = 80, nullable = false )
	private String logradouro;

	@Column( name = "numero", length = 5, nullable = false )
	private String numero;

	@Column( name = "bairro", length = 30, nullable = false )
	private String bairro;

	@Column( name = "cidade", length = 25, nullable = false )
	private String cidade;

	@Column( name = "cep", length = 11, nullable = false )
	private String cep;

	@Column( name = "uf", length = 2, nullable = false )
	private String uf;

	//GETTERS E SETTERS
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro( String logradouro ) {
		this.logradouro = Utilidades.normalizeString( logradouro );
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero( String numero ) {
		this.numero = Utilidades.normalizeString( numero );
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro( String bairro ) {
		this.bairro = Utilidades.normalizeString( bairro );
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade( String cidade ) {
		this.cidade = Utilidades.normalizeString( cidade );
	}

	public String getCep() {
		return cep;
	}

	public void setCep( String cep ) {
		this.cep = Utilidades.normalizeString( cep );
	}

	public String getUf() {
		return uf;
	}

	public void setUf( String uf ) {
		this.uf = Utilidades.normalizeString( uf );
	}

	//HASHCODE E EQUALS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( bairro == null ) ? 0 : bairro.hashCode() );
		result = prime * result + ( ( cep == null ) ? 0 : cep.hashCode() );
		result = prime * result + ( ( cidade == null ) ? 0 : cidade.hashCode() );
		result = prime * result + ( ( logradouro == null ) ? 0 : logradouro.hashCode() );
		result = prime * result + ( ( numero == null ) ? 0 : numero.hashCode() );
		result = prime * result + ( ( uf == null ) ? 0 : uf.hashCode() );
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
		if ( !( obj instanceof EnderecoPO ) ) {
			return false;
		}
		EnderecoPO other = (EnderecoPO) obj;
		if ( bairro == null ) {
			if ( other.bairro != null ) {
				return false;
			}
		} else if ( !bairro.equals( other.bairro ) ) {
			return false;
		}
		if ( cep == null ) {
			if ( other.cep != null ) {
				return false;
			}
		} else if ( !cep.equals( other.cep ) ) {
			return false;
		}
		if ( cidade == null ) {
			if ( other.cidade != null ) {
				return false;
			}
		} else if ( !cidade.equals( other.cidade ) ) {
			return false;
		}
		if ( logradouro == null ) {
			if ( other.logradouro != null ) {
				return false;
			}
		} else if ( !logradouro.equals( other.logradouro ) ) {
			return false;
		}
		if ( numero == null ) {
			if ( other.numero != null ) {
				return false;
			}
		} else if ( !numero.equals( other.numero ) ) {
			return false;
		}
		if ( uf == null ) {
			if ( other.uf != null ) {
				return false;
			}
		} else if ( !uf.equals( other.uf ) ) {
			return false;
		}
		return true;
	}

	//COMPARETOR
	@Override
	public int compareTo( EnderecoPO o ) {
		return getLogradouro().compareTo( o.getLogradouro() );
	}

	//TOSTRING
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "EnderecoPO [logradouro=" );
		builder.append( logradouro );
		builder.append( ", numero=" );
		builder.append( numero );
		builder.append( ", bairro=" );
		builder.append( bairro );
		builder.append( ", cidade=" );
		builder.append( cidade );
		builder.append( ", cep=" );
		builder.append( cep );
		builder.append( ", uf=" );
		builder.append( uf );
		builder.append( ", getId()=" );
		builder.append( getId() );
		builder.append( "]" );
		return builder.toString();
	}

}
