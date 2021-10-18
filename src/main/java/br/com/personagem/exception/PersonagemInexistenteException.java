package br.com.personagem.exception;

public class PersonagemInexistenteException extends PersonagemException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public PersonagemInexistenteException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public PersonagemInexistenteException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public PersonagemInexistenteException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public PersonagemInexistenteException(Throwable cause) {
		super(cause);
	}
	
}
