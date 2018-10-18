package com.cedei.plexus.appusers.exceptions;

/**
 * BadRequestException
 */
public class BadRequestException extends RuntimeException{


    
    private static final long serialVersionUID = -3812694901381472696L;

    public BadRequestException(String message) {
        super(message);
    }

    
}