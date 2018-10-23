package com.cedei.plexus.appusers.exceptions.rest;

import org.springframework.http.HttpStatus;

/**
 * BadRequestException
 * 
 * Se lanzará esta excepcion cuando le capture una EmptyBodyExcepcion
 * 
 * @author Ayoze Martin Hdez
 * @version 0.0.1
 */
public class BadRequestException extends RestException {

    /**
     * Constructor
     */
    public BadRequestException() {
        super(HttpStatus.BAD_REQUEST);
        this.message = "El formato de la peticion es inválido";
    }

}