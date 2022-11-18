package org.subit.sms.handler.Exception;

import org.subit.sms.dto.ReturnCode;

public class UsernameNotFoundException extends SMSBaseException{
    @Override
    public ReturnCode getReturnCode() {
        return ReturnCode.UsernamePasswordNotMatch;
    }
}
