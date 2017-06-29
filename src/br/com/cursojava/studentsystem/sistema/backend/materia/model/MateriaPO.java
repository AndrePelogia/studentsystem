package br.com.cursojava.studentsystem.sistema.backend.materia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.cursojava.studentsystem.abstracts.AbstractPO;
import br.com.cursojava.studentsystem.interfaces.Crud;
import br.com.cursojava.studentsystem.utilidades.Utilidades;

@Entity
@Table( name = "materias", schema = Crud.SCHEMA )
public class MateriaPO extends AbstractPO< MateriaPO >{

	@Column( name = "nome", length = 40, nullable = false )
	private String nome;

	@Column( name = "professor", length = 70, nullable = false )
	private String professor;

	//GETTERS AND SETTERS
	public String getNome() {
		return nome;
	}

	public void setNome( String nome ) {
		this.nome = Utilidades.normalizeString( nome );
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor( String professor ) {
		this.professor = Utilidades.normalizeString( professor );
	}

	//TO STRING
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "MateriaPO [nome=" );
		builder.append( nome );
		builder.append( ", professor=" );
		builder.append( professor );
		builder.append( "]" );
		return builder.toString();
	}

	//HASHCODE EQUALS

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( nome == null ) ? 0 : nome.hashCode() );
		result = prime * result + ( ( professor == null ) ? 0 : professor.hashCode() );
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
		if ( !( obj instanceof MateriaPO ) ) {
			return false;
		}
		MateriaPO other = (MateriaPO) obj;
		if ( nome == null ) {
			if ( other.nome != null ) {
				return false;
			}
		} else if ( !nome.equals( other.nome ) ) {
			return false;
		}
		if ( professor == null ) {
			if ( other.professor != null ) {
				return false;
			}
		} else if ( !professor.equals( other.professor ) ) {
			return false;
		}
		return true;
	}

	//COMPARATE TO
	@Override
	public int compareTo( MateriaPO o ) {
		return getNome().compareTo( o.getNome() );
	}

}
