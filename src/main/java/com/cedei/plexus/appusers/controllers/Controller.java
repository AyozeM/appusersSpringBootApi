package com.cedei.plexus.appusers.controllers;

import com.cedei.plexus.appusers.exceptions.java.EmptyBodyException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Controller
 * 
 * Clase padre de todos los controladores de la api
 * 
 * @author Ayoze Martin Hdez
 * @version 0.0.1
 */
public abstract class Controller {

    /**
     * Nombre del recurso
     */
    protected String resource;

    /**
     * Convertidor de json a objeto
     */
    protected ObjectMapper converter = new ObjectMapper();

    /**
     * Comprueba que el cuerpo de la petición no está vacio
     * 
     * @param toCheck cuerpo del request
     * @throws EmptyBodyException
     */
    protected void checkEmptyBody(Object toCheck) throws EmptyBodyException {
        if (toCheck == null) {
            throw new EmptyBodyException();
        }
    }
}