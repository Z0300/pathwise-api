package com.api.pathwise.exception;

import org.springframework.http.HttpStatus;

public class DuplicateResourceException extends ApiException {
    public DuplicateResourceException(String message) {
        super(message);
    }
    public HttpStatus getStatus() { return HttpStatus.CONFLICT; }
    public String getErrorCode() { return "DUPLICATE_RESOURCE"; }
}
