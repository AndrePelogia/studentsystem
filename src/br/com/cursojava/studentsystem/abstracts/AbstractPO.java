package br.com.cursojava.studentsystem.abstracts;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * MappedSuperclass
 * Mapeia esta classe coo apenas uma classe de herança, onde todos seus
 * atributos serão implementados nas tabelas que representam as classes Filhas.
 *
 * @param <T>
 *
 * @author André Duarte <andre.silva141@fatec.sp.gov.br>
 * @since 11 de abr de 2017 22:18:37
 * @version 1.0
 */
@MappedSuperclass
public abstract class AbstractPO< T > implements Comparable< T >{

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	@Override
	public abstract int hashCode();

	@Override
	public abstract boolean equals( Object obj );

	@Override
	public abstract String toString();
}
