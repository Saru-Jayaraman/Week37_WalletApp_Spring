package se.lexicon.exception;

public class WalletValidationException extends RuntimeException {

    private String paramName;

    public WalletValidationException(String message) {
        super(message);
    }

    public WalletValidationException(String message, String paramName) {
        super(message);
        this.paramName = paramName;
    }

    public WalletValidationException(String message, Throwable cause, String paramName) {
        super(message, cause);
        this.paramName = paramName;
    }

    public String getParamName() {
        return paramName;
    }
}
