package com.cedei.plexus.appusers.exceptions.rest;

import java.util.Date;

import org.springframework.http.HttpStatus;

/**
 * RestException Clase padre de las excepciones que se devolverán al usuario
 * 
 * @author Ayoze Martin Hdez
 * @version 0.0.1
 */
public abstract class RestException {

    /**
     * Mensaje para el usuario
     */
    protected String message;

    /**
     * Código HTTO
     */
    protected HttpStatus status;

    /**
     * Fecha en la que se origino la excepcion
     */
    protected Date date;

    /**
     * Constructor
     * 
     * @param status estado http
     */
    public RestException(HttpStatus status) {
        this.status = status;
        this.date = new Date();
    }

    /**
     * Getter de mensaje
     * 
     * @return mensaje
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Getter para el estado
     * 
     * @return estado
     */
    public HttpStatus getStatus() {
        return this.status;
    }

    /**
     * Getter para la fecha
     * 
     * @return fecha
     */
    public Date getDate() {
        return this.date;
    }

}