package group.bigone.api.global.error.exception;

public enum ErrorCode {
    // Common
    INVALID_INPUT_VALUE(10000, "Invalid Input Value", 400),
    METHOD_NOT_ALLOWED(10001, "Invalid Input Value", 405),
    ENTITY_NOT_FOUND(10002, "Entity Not Found", 400),
    INTERNAL_SERVER_ERROR(10003, "Server Error", 500),
    INVALID_TYPE_VALUE(10004, "Invalid Type Value", 400),
    HANDLE_ACCESS_DENIED(10005, "Access is Denied", 403),
    HANDLE_AUTHENTICATION_ENTRY_POINT(10006, "Authentication Entry Point", 403),
    ERROR_COMMUNICATION(10007, "An error occurred during communication", 500),

    // User
    USER_NOT_FOUND(20001, "User not Found", 500),
    ;

    private final Integer code;
    private final String message;
    private Integer status;


    ErrorCode(Integer code, String message, Integer status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public Integer getCode() {
        return code;
    }

    public Integer getStatus() {
        return status;
    }
}
