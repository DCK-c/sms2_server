package org.subit.sms.dto;

/*
code
1xxxx - success
2xxxx - failed
3xxxx - Privilege Problems
5xxxx - error
9xxxx - test
 */
public enum ReturnCode {
    Success(10000, "success"),
    UnknownFailed(20000, "Unknown Failed"),
    UsernamePasswordError(20001, "username or password is not correct"),

    NoPrivileges(30000, "No Privilege"),
    UserForbidden(30001, "You are blocked to login"),
    AccountInactivated(30002, "Account Inactivated"),

    UnknownError(50000, "Server Unknown Error"),
    InvalidMethod(50001, "Invalid Method"),
    InvalidParams(50002, "Invalid Request Params"),

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
