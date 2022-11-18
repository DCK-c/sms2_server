package org.subit.sms.handler.Exception;

import org.subit.sms.dto.ReturnCode;

public class UsernamePasswordNotMatchException extends AuthException {
    @Override
    public ReturnCode getReturnCode() {
        return ReturnCode.UsernamePasswordNotMatch;
    }
}
