package org.subit.sms.Exception;

import com.sun.istack.NotNull;
import org.springframework.lang.NonNull;
import org.subit.sms.dto.ReturnCode;

public abstract class SMSBaseException extends Exception {


    @NonNull
    public abstract ReturnCode getReturnCode();
}
