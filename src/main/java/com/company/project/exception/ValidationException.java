package com.company.project.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String msg){
        super(msg);
    }
}
