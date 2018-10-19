package com.cedei.plexus.appusers.controllers;

import com.cedei.plexus.appusers.exceptions.java.EmptyBodyException;
import com.cedei.plexus.appusers.exceptions.java.ResourceExists;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * controller
 */
public abstract class Controller {

    protected String resource;
    protected void checkEmptyBody(Object toCheck) throws EmptyBodyException{
        if (toCheck == null) {
            throw new EmptyBodyException();
        }
    }

    protected void exists(Integer id, Boolean mustExists, JpaRepository repository) throws ResourceExists {
        Boolean result = id != null && repository.findById(id).isPresent();

        if ((result && !mustExists) || (!result && mustExists)) {
            throw new ResourceExists(resource, id, mustExists);
        }
    }

}