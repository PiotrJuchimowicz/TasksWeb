package com.company.project.exception;

public class UnableToMigrateException extends RuntimeException {
    public UnableToMigrateException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
