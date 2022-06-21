package com.rdacompany.RDAsportsAPI.exception;

public class AreaNotFoundException extends Exception {
    private static final String DEFAULT_ERROR_MESSAGE = "Area no encontrada";

    //hago dos metodos uno para poder pasar un mensaje y otro que no pasa un mensaje por defecto.
    public AreaNotFoundException(String message) {
        super(message);
    }

    public AreaNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }
}

