package group.bigone.api.advice.exception;

public class NoMatchIDCardException extends RuntimeException {
    public NoMatchIDCardException() {
        super();
    }

    public NoMatchIDCardException(String msg) {
        super(msg);
    }

    public NoMatchIDCardException(String msg, Throwable t) {
        super(msg,t);
    }
}
