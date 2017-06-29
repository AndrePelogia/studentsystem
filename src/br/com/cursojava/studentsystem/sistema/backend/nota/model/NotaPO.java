package br.com.cursojava.studentsystem.sistema.backend.nota.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

import br.com.cursojava.studentsystem.abstracts.AbstractPO;
import br.com.cursojava.studentsystem.interfaces.Crud;
import br.com.cursojava.studentsystem.sistema.backend.aluno.model.AlunoPO;
import br.com.cursojava.studentsystem.sistema.backend.materia.model.MateriaPO;
import br.com.cursojava.studentsystem.utilidades.Utilidades;

@Entity
@Table( name = "notas", schema = Crud.SCHEMA )
public class NotaPO extends AbstractPO< NotaPO >{

	@Column( name = "nota", precision = 5, scale = 2, nullable = false )
	private BigDecimal nota;

	@Column( name = "observacao", length = 255, nullable = true )
	private String observacao;

	@Temporal( TemporalType.DATE )
	@Column( name = "data", length = 10, nullable = false )
	private Date data;

	/**
	 * 
	 * Atributo de relacionamento entre Nota e Materia
	 * Neste caso está sendo configurado um AGREGAÇÃO POR COMPOSIÇÃO
	 */
	//SEMPRE QUE TRAZER O NOTA ELE TRAS A MATERIA, TUDO QUE FAZ NA NOTA ELE REFLETO NA MATERIA
	@ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, optional = false )
	@JoinColumn( name = "idMateria" )
	@ForeignKey( name = "fk_nota_materia" )
	private MateriaPO materia = new MateriaPO();

	//RELACIONAMENTO MANY TO ONE ENTRE NOTA E ALUNO
	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	@JoinColumn( name = "idAluno" )
	@ForeignKey( name = "fk_nota_aluno" )
	private AlunoPO aluno = new AlunoPO();

	//GETTERS AND SETTERS

	public BigDecimal getNota() {
		return nota;
	}

	public AlunoPO getAluno() {
		return aluno;
	}

	public void setAluno( AlunoPO aluno ) {
		this.aluno = aluno;
	}

	public MateriaPO getMateria() {
		return materia;
	}

	public void setMateria( MateriaPO materia ) {
		this.materia = materia;
	}

	public void setNota( BigDecimal nota ) {
		this.nota = nota;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao( String observacao ) {
		this.observacao = Utilidades.normalizeString( observacao );
	}

	public Date getData() {
		return data;
	}

	public void setData( Date data ) {
		this.data = data;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "NotaPO [nota=" );
		builder.append( nota );
		builder.append( ", observacao=" );
		builder.append( observacao );
		builder.append( ", data=" );
		builder.append( data );
		builder.append( ", materia=" );
		builder.append( materia );
		builder.append( ", aluno=" );
		builder.append( aluno );
		builder.append( "]" );
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( aluno == null ) ? 0 : aluno.hashCode() );
		result = prime * result + ( ( data == null ) ? 0 : data.hashCode() );
		result = prime * result + ( ( materia == null ) ? 0 : materia.hashCode() );
		result = prime * result + ( ( nota == null ) ? 0 : nota.hashCode() );
		result = prime * result + ( ( observacao == null ) ? 0 : observacao.hashCode() );
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
		if ( !( obj instanceof NotaPO ) ) {
			return false;
		}
		NotaPO other = (NotaPO) obj;
		if ( aluno == null ) {
			if ( other.aluno != null ) {
				return false;
			}
		} else if ( !aluno.equals( other.aluno ) ) {
			return false;
		}
		if ( data == null ) {
			if ( other.data != null ) {
				return false;
			}
		} else if ( !data.equals( other.data ) ) {
			return false;
		}
		if ( materia == null ) {
			if ( other.materia != null ) {
				return false;
			}
		} else if ( !materia.equals( other.materia ) ) {
			return false;
		}
		if ( nota == null ) {
			if ( other.nota != null ) {
				return false;
			}
		} else if ( !nota.equals( other.nota ) ) {
			return false;
		}
		if ( observacao == null ) {
			if ( other.observacao != null ) {
				return false;
			}
		} else if ( !observacao.equals( other.observacao ) ) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo( NotaPO o ) {
		return o.getNota().compareTo( o.getNota() );
	}

}
