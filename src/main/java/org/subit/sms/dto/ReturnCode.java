package org.subit.sms.dto;

/*
code
1xxxx - success
5xxxx - error
9xxxx - test
 */
public enum ReturnCode {
    success(10000, "success"),
    UnknownError(50000, "Server Unknown Error"),
    testAPI(99999, "Test API");
    private final int code;
    private final String message;

    ReturnCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
