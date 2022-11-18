package org.subit.sms.handler.Exception;

import org.subit.sms.dto.ReturnCode;

public class CaptchaException extends SMSBaseException{
    @Override
    public ReturnCode getReturnCode() {
        return ReturnCode.CaptchaError;
    }
}
