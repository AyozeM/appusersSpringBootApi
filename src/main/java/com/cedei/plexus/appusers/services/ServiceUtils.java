package com.cedei.plexus.appusers.services;

import java.util.Optional;

import com.cedei.plexus.appusers.exceptions.java.ResourceExists;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Service
 */
public class ServiceUtils {

    protected String resource;
    /**
     * Comprueba si un elemento existe o no
     * 
     * @param id         identificador de recurso
     * @param mustExists marca si el recurso tiene que existir o no
     * @param repository repositorio en el cual buscar el recurso
     * @throws ResourceExists
     */
    protected Optional exists(Integer id, Boolean mustExists, JpaRepository repository) throws ResourceExists {
        Optional aux = repository.findById(id);
        Boolean result = id != null && aux.isPresent();

        if ((result && !mustExists) || (!result && mustExists)) {
            throw new ResourceExists("resource", id, mustExists);
        }
        return aux;
    }
}