package org.subit.sms.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.subit.sms.dto.Response;
import org.subit.sms.dto.ReturnCode;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Response<Object> BaseException(Exception e) {
        return new Response<>(ReturnCode.UnknownError, e.getClass().getName() + " " + e.getMessage());
    }
}
