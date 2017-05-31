package br.com.cursojava.studentsystem.exception;

public final class ApplicationException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7414368462678996139L;

	public ApplicationException( String message, Throwable cause ){
		super( message, cause );
	}

	public ApplicationException( String message ){
		super( message );
	}

}
