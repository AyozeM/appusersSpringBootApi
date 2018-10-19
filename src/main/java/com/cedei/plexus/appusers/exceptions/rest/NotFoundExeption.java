package com.cedei.plexus.appusers.exceptions.rest;

import org.springframework.http.HttpStatus;

/**
 * NotFoundExeption
 */
public class NotFoundExeption extends RestException {

    public NotFoundExeption(String message) {
        super(HttpStatus.NOT_FOUND);
        this.message = message;
    }
    
}