package com.rdacompany.RDAsportsAPI.exception;

public class SessionNotFoundException extends Exception {
    private static final String DEFAULT_ERROR_MESSAGE = "Session not found";

    public SessionNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }
}
