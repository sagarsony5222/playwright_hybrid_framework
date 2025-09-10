package exceptions;

public class FrameworkException extends RuntimeException {
    private final String errorCode;

    public FrameworkException(String message) {
        super(message);
        this.errorCode = null;
    }

    public FrameworkException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = null;
    }

    public FrameworkException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public FrameworkException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
