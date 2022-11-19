package org.subit.sms.Exception;

import org.subit.sms.dto.ReturnCode;

public class PasswordNotMatchException extends AuthException{

    @Override
    public ReturnCode getReturnCode() {
        return ReturnCode.PasswordError;
    }
}
