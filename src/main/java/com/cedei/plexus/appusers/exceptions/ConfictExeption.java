package com.cedei.plexus.appusers.exceptions;

import java.util.Date;

/**
 * ConfictExeption
 */
public class ConfictExeption {


    private String message;
    private Date date = new Date();

    public ConfictExeption(String message) {
        this.message = message;
    }
    
}