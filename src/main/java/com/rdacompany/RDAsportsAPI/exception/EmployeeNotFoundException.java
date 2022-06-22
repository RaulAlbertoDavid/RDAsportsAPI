package com.rdacompany.RDAsportsAPI.exception;

public class EmployeeNotFoundException extends Exception{
    private static final String DEFAULT_ERROR_MESSAGE = "Employee not found";

    public EmployeeNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }
}
