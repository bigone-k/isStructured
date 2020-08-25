package group.bigone.api.global.advice.exception;

public class CustomAuthenticationEntryPointException extends RuntimeException {
    public CustomAuthenticationEntryPointException(String message, Throwable throwable)
    {
        super(message, throwable);
    }
    public CustomAuthenticationEntryPointException(String message)
    {
        super(message);
    }
    public CustomAuthenticationEntryPointException()
    {
        super();
    }
}
