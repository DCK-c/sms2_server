package org.subit.sms.Exception;

import org.subit.sms.dto.ReturnCode;

public class UserForbiddenException extends AuthException {
    @Override
    public ReturnCode getReturnCode() {
        return ReturnCode.UserForbidden;
    }
}
