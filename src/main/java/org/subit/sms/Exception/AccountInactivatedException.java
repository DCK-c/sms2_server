package org.subit.sms.Exception;

import org.subit.sms.dto.ReturnCode;

public class AccountInactivatedException extends AuthException {

    @Override
    public ReturnCode getReturnCode() {
        return ReturnCode.AccountInactivated;
    }
}
