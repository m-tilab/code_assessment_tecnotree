package io.project.exception;

public class RequiredParameterException extends RuntimeException {
    public RequiredParameterException(String parameter) {
        super(String.format("%s is required", parameter));

    }
}
