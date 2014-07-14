package by.filippov.newconcurrent.exceptions;

public class LogicalException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LogicalException(String message, Throwable cause) {
		super(message, cause);
	}

	public LogicalException(String message) {
		super(message);
	}

	public LogicalException(Throwable cause) {
		super(cause);
	}

}
