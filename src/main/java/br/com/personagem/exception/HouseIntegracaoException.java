package br.com.personagem.exception;

public class HouseIntegracaoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public HouseIntegracaoException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public HouseIntegracaoException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public HouseIntegracaoException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public HouseIntegracaoException(Throwable cause) {
		super(cause);
	}

}
