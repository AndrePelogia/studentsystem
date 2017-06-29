package br.com.cursojava.studentsystem.sistema.backend.turma.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import br.com.cursojava.studentsystem.abstracts.AbstractPO;
import br.com.cursojava.studentsystem.interfaces.Crud;
import br.com.cursojava.studentsystem.utilidades.Utilidades;

@Entity
@Table( name = "turmas", schema = Crud.SCHEMA, uniqueConstraints = @UniqueConstraint( columnNames = { "nome", "dataInicio", "dataTermino" } ) )
public class TurmaPO extends AbstractPO< TurmaPO >{

	@Column( name = "nome", length = 80, nullable = false )
	private String nome;

	@Temporal( TemporalType.DATE )
	@Column( name = "dataInicio", length = 10, nullable = false )
	private Date dataInicio;

	@Temporal( TemporalType.DATE )
	@Column( name = "dataTermino", length = 10, nullable = false )
	private Date dataTermino;

	//SETTERS AND GETTERS

	public String getNome() {
		return nome;
	}

	public void setNome( String nome ) {
		this.nome = Utilidades.normalizeString( nome );
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio( Date dataInicio ) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino( Date dataTermino ) {
		this.dataTermino = dataTermino;
	}

	@Override
	public int compareTo( TurmaPO o ) {
		return this.getNome().compareToIgnoreCase( o.getNome() );
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( dataInicio == null ) ? 0 : dataInicio.hashCode() );
		result = prime * result + ( ( dataTermino == null ) ? 0 : dataTermino.hashCode() );
		result = prime * result + ( ( nome == null ) ? 0 : nome.hashCode() );
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
		if ( !( obj instanceof TurmaPO ) ) {
			return false;
		}
		TurmaPO other = (TurmaPO) obj;
		if ( dataInicio == null ) {
			if ( other.dataInicio != null ) {
				return false;
			}
		} else if ( !dataInicio.equals( other.dataInicio ) ) {
			return false;
		}
		if ( dataTermino == null ) {
			if ( other.dataTermino != null ) {
				return false;
			}
		} else if ( !dataTermino.equals( other.dataTermino ) ) {
			return false;
		}
		if ( nome == null ) {
			if ( other.nome != null ) {
				return false;
			}
		} else if ( !nome.equals( other.nome ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "TurmaPO [nome=" );
		builder.append( nome );
		builder.append( ", dataInicio=" );
		builder.append( dataInicio );
		builder.append( ", dataTermino=" );
		builder.append( dataTermino );
		builder.append( "]" );
		return builder.toString();
	}

}
