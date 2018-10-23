package com.cedei.plexus.appusers.exceptions.rest;

import org.springframework.http.HttpStatus;

/**
 * ConfictExeption
 * 
 * Se lanzará esta excepción cuando el se intente añadir un recurso que ya
 * existe
 * 
 * @author Ayoze Martin Hdez
 * @version 0.0.1
 */
public class ConfictExeption extends RestException {

    /**
     * Constructor
     * 
     * @param message mensaje para el usuario
     */
    public ConfictExeption(String message) {
        super(HttpStatus.CONFLICT);
        this.message = message;
    }

}