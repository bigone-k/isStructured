package group.bigone.api.advice.exception;

public class CUserNotFoundException extends RuntimeException {
    public CUserNotFoundException() {
        super();
    }

    public CUserNotFoundException(String msg) {
        super(msg);
    }

    public CUserNotFoundException(String msg, Throwable t) {
        super(msg,t);
    }
}
