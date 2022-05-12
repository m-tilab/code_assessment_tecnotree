package io.project.exception;

public class RequiredRequestParameterException extends RuntimeException {
    public RequiredRequestParameterException(String requestParameter) {
        super(String.format("%s is required as request parameter", requestParameter));
    }
}
