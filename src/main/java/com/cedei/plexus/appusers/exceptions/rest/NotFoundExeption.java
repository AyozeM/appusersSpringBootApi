package com.cedei.plexus.appusers.exceptions.rest;

import org.springframework.http.HttpStatus;

/**
 * NotFoundExeption
 * 
 * Se lanzará esta excepcion cuando se intente operar sobre un recurso
 * inexistente
 * 
 * @author Ayoze Martin Hdez
 * @version 0.0.1
 */
public class NotFoundExeption extends RestException {

    /**
     * Constructor
     * 
     * @param resource recurso sobre el que se intento operar
     * @param id       identificador de recurso
     */
    public NotFoundExeption(String resource, Integer id) {
        super(HttpStatus.NOT_FOUND);
        this.message = String.format("%s con identificador %d no existe", resource, id);
    }

}