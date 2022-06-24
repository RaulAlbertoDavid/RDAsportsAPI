package com.rdacompany.RDAsportsAPI.exception;

public class CustomerNotFoundException extends Exception {
    private static final String DEFAULT_ERROR_MESSAGE = "Costumer not found";

    public CustomerNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }
}
