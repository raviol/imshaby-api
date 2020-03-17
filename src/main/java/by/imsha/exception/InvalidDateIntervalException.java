package by.imsha.exception;

/**
 * For HTTP 404 errros
 */
public class InvalidDateIntervalException extends RuntimeException {
    public InvalidDateIntervalException() {
        super();
    }

    public InvalidDateIntervalException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDateIntervalException(String message) {
        super(message);
    }

    public InvalidDateIntervalException(Throwable cause) {
        super(cause);
    }

}
