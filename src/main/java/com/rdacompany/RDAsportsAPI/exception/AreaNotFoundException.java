package com.rdacompany.RDAsportsAPI.exception;

public class AreaNotFoundException extends Exception {
    private static final String DEFAULT_ERROR_MESSAGE = "Area not found";

    public AreaNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }
}

