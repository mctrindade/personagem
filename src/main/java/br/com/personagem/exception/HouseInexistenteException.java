package br.com.personagem.exception;

public class HouseInexistenteException extends PersonagemException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public HouseInexistenteException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public HouseInexistenteException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public HouseInexistenteException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public HouseInexistenteException(Throwable cause) {
		super(cause);
	}
	
}
