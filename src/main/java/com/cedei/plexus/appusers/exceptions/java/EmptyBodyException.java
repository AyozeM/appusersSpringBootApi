package com.cedei.plexus.appusers.exceptions.java;

/**
 * EmptyBodyException
 */
public class EmptyBodyException extends Exception{


    private static final long serialVersionUID = 1L;

    public EmptyBodyException() {
        super("El cuerpo del mensaje esta vacio");
    }

    
}