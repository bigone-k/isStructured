package group.bigone.api.advice.exception;

public class NoDataCodeException extends RuntimeException {
    public NoDataCodeException() {
        super();
    }

    public NoDataCodeException(String msg) {
        super(msg);
    }

    public NoDataCodeException(String msg, Throwable t) {
        super(msg,t);
    }
}
