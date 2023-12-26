package sa.com.mysolutions.stcs.http.api;

public class SystemException extends Exception {
	
	/** The Constant serialVersionUID. **/
	private static final long serialVersionUID = 1854174299110193609L;

	/**
	 * Instantiates a new  system exception.
	 */
	public SystemException() {
		super();
	}

	/**
	 * Instantiates a new  system exception.
	 */
	public SystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Instantiates a new  system exception.
	 */
	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new  system exception.
	 */
	public SystemException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new  system exception.
	 */
	public SystemException(Throwable cause) {
		super(cause);
	}

}
