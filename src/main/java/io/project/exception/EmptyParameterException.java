package io.project.exception;

public class EmptyParameterException extends RuntimeException {
    public EmptyParameterException(String parameter) {
        super(String.format("parameter %s can't be empty", parameter));
    }
}
