package com.api.pathwise.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
    public HttpStatus getStatus() { return HttpStatus.NOT_FOUND; }
    public String getErrorCode() { return "NOT_FOUND"; }
}
