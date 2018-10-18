package com.cedei.plexus.appusers.exceptions;

/**
 * NotFoundExeption
 */
public class NotFoundExeption extends RuntimeException {

    private static final long serialVersionUID = 9178714772374000235L;

    public NotFoundExeption(String message) {
        super(message);
    }
    
}