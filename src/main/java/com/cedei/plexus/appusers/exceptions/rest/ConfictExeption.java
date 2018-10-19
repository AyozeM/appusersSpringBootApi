package com.cedei.plexus.appusers.exceptions.rest;

import org.springframework.http.HttpStatus;

/**
 * ConfictExeption
 */
public class ConfictExeption extends RestException{

    public ConfictExeption(String message) {
        super(HttpStatus.CONFLICT);
        this.message = message;
    }
    
}