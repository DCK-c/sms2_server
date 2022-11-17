package org.subit.sms.handler;

import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.subit.sms.dto.Response;
import org.subit.sms.dto.ReturnCode;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //Other Unhandled Exceptions
    @ExceptionHandler(Exception.class)
    public Response BaseException(Exception e) {
        e.printStackTrace();
        return new Response(ReturnCode.UnknownError, e.getClass().getName() + "\n" + e.getMessage());
    }

    //Invalid Request Methods
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Response invalidMethods(HttpRequestMethodNotSupportedException e) {
        return new Response(ReturnCode.InvalidMethod, e.getMessage());
    }

    //Request Params could not be serialized
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Response invalidRequestPrams(HttpMessageNotReadableException e) {
        return new Response(ReturnCode.InvalidParams, null);
    }

}
