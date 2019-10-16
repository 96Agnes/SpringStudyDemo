package com.example.test.springtest.exception;

public class CustomizeException extends RuntimeException{
    private Integer code;
    public Integer getCode() {
        return code;
    }

    private String message;
    @Override
    public String getMessage() {
        return message;
    }

    public CustomizeException(ICustomizeErrorCode errorCode){
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }
}
