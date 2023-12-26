package sa.com.mysolutions.audit.exception;

/**
 * @author Andrea Di Giorgi
 */
public class NoSuchAuditException extends Exception {

	public NoSuchAuditException() {
	}

	public NoSuchAuditException(String msg) {
		super(msg);
	}

	public NoSuchAuditException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchAuditException(Throwable throwable) {
		super(throwable);
	}

}