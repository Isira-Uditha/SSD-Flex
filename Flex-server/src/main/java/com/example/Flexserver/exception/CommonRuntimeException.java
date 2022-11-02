package com.example.Flexserver.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String exceptionCode;
    private Object[] placeholderValues;
    private Object data;

    public CommonRuntimeException(String exceptionCode) {
        super(exceptionCode);
        setExceptionCode(exceptionCode);
    }

    public CommonRuntimeException(String exceptionCode, Object data){
        super(exceptionCode);
        setExceptionCode(exceptionCode);
        setData(data);
    }

    public CommonRuntimeException(Throwable cause, String exceptionCode) {
        super(exceptionCode, cause);
        setExceptionCode(exceptionCode);
    }


    public CommonRuntimeException(Throwable cause, String exceptionCode, Object[] placeholderValues) {
        this(cause, exceptionCode);
        setPlaceholderValues(placeholderValues);
    }

    public CommonRuntimeException(String exceptionCode, Object[] placeholderValues) {
        this(exceptionCode);
        setPlaceholderValues(placeholderValues);
    }

}