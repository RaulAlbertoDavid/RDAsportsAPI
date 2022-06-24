package com.rdacompany.RDAsportsAPI.exception;

public class ActivityNotFoundExcepction extends Exception{
    private static final String DEFAULT_ERROR_MESSAGE = "Activity not found";

    public ActivityNotFoundExcepction() {
        super(DEFAULT_ERROR_MESSAGE);
    }
}
