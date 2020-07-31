package group.bigone.api.advice.exception;

public class FailScrapIDCardException extends RuntimeException {
    public FailScrapIDCardException() {
        super();
    }

    public FailScrapIDCardException(String msg) {
        super(msg);
    }

    public FailScrapIDCardException(String msg, Throwable t) {
        super(msg,t);
    }
}
