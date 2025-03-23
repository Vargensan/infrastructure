package com.waf.infrastructure.security.authentication;

public class UnauthenticatedException extends RuntimeException {

    public UnauthenticatedException(String message) {
        super(message);
    }

}
