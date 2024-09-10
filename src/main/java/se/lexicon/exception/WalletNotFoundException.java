package se.lexicon.exception;

public class WalletNotFoundException extends RuntimeException {

    private String paramName;

    public WalletNotFoundException(String message) {
        super(message);
    }

    public WalletNotFoundException(String message, String paramName) {
        super(message);
        this.paramName = paramName;
    }

    public WalletNotFoundException(String message, Throwable cause, String paramName) {
        super(message, cause);
        this.paramName = paramName;
    }
}
