package com.company.project.exception;

public class ObjectAlreadyExistsException extends RuntimeException {
    public ObjectAlreadyExistsException(String msg) {
        super(msg);
    }
}
