package br.com.personagem.exception;

public class PersonagemException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public PersonagemException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public PersonagemException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public PersonagemException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public PersonagemException(Throwable cause) {
		super(cause);
	}
	
}
