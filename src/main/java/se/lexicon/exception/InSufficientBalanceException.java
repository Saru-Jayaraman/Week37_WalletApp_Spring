package se.lexicon.exception;

public class InSufficientBalanceException extends RuntimeException {
    public InSufficientBalanceException(String message) {
        super(message);
    }

    public InSufficientBalanceException(String message, Throwable cause) {
        super(message, cause);
    }
}
