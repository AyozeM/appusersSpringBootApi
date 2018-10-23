package com.cedei.plexus.appusers.controllers;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

/**
 * ControllerInterface
 * 
 * Interfaz para controladores
 * 
 * @author Ayoze Martin Hdez
 * @version 0.0.1
 */
public interface ControllerInterface {

    /**
     * servira para añadir un recurso
     * 
     * @param request se debe recibir json con los datos del recurso
     * @return recurso añadido o excepcion
     */
    public ResponseEntity<?> add(RequestEntity<?> request);

    /**
     * Servirá para obtener todos los elementos de un determinado recurso
     * 
     * @param request
     * @return lista de elementos o excepcion
     */
    public ResponseEntity<?> getAll(RequestEntity<?> request);

    /**
     * Servirá para obtener un recurso por su id
     * 
     * @param id identificador de recurso
     * @return el recurso buscado o excepcion
     */
    public ResponseEntity<?> findById(Integer id);

    /**
     * Servirá para actualizar un recurso
     * 
     * @param request se debe recibir un json con los datos del recurso ya
     *                actualizados
     * @return el recurso actualizado o excepcion
     */
    public ResponseEntity<?> update(RequestEntity<?> request);

    /**
     * Servirá para eliminar un recurso
     * 
     * @param id identificador de recurso
     * @return string de confirmacion o excepcion
     */
    public ResponseEntity<?> remove(Integer id);

}