package com.cedei.plexus.appusers.exceptions.java;

/**
 * EmptyBodyException
 * 
 * Se lanzará esta excepcion cuando el cuerpo de la request sea nulo
 * 
 * @author Ayoze Martin Hdez
 * @version 0.0.1
 */
public class EmptyBodyException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     */
    public EmptyBodyException() {
        super("El cuerpo del mensaje esta vacio");
    }

}