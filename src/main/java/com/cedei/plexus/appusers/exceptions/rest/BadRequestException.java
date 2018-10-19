package com.cedei.plexus.appusers.exceptions.rest;

import org.springframework.http.HttpStatus;

/**
 * BadRequestException
 */
public class BadRequestException extends RestException{

    public BadRequestException() {
        super(HttpStatus.BAD_REQUEST);
        this.message = "El formato de la peticion es inválido";
    }
    
}