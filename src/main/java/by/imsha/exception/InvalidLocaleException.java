package by.imsha.exception;

/**
 * For HTTP 404 errros
 */
public class InvalidLocaleException extends RuntimeException {
    public InvalidLocaleException() {
        super();
    }

    public InvalidLocaleException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidLocaleException(String message) {
        super(message);
    }

    public InvalidLocaleException(Throwable cause) {
        super(cause);
    }

}
