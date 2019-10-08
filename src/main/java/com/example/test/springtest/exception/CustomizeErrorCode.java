package com.example.test.springtest.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOT_FOUND("问题不存在或已被删除");

    private String message;
    @Override
    public String getMessage() {
        return message;
    }
    CustomizeErrorCode(String message){
        this.message = message;
    }
}
