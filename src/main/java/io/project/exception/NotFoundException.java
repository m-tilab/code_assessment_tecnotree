package io.project.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String object, Long objectId) {
        super(String.format("%s with id %s not found", object,objectId));
    }
}
