package com.cedei.plexus.appusers.controllers;

import com.cedei.plexus.appusers.exceptions.java.EmptyBodyException;
import com.cedei.plexus.appusers.exceptions.java.ResourceExists;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.data.jpa.repository.JpaRepository;

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

    /**
     * Comprueba si un elemento existe o no
     * 
     * @param id         identificador de recurso
     * @param mustExists marca si el recurso tiene que existir o no
     * @param repository repositorio en el cual buscar el recurso
     * @throws ResourceExists
     */
    protected void exists(Integer id, Boolean mustExists, JpaRepository repository) throws ResourceExists {
        Boolean result = id != null && repository.findById(id).isPresent();

        if ((result && !mustExists) || (!result && mustExists)) {
            throw new ResourceExists(resource, id, mustExists);
        }
    }

}