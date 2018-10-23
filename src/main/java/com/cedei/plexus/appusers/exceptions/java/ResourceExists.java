package com.cedei.plexus.appusers.exceptions.java;

/**
 * ResourceExists
 * 
 * Se lanzará esta excepcion cuando no exista el usuario y deba existir y
 * viceversa
 * 
 * @author Ayoze Martin Hdez
 * @version 0.0.1
 */
public class ResourceExists extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * 
     * @param resource recurso que se está evaluando
     * @param id       identificador de recurso
     * @param exists   booleano que decidirá si existe o no
     */
    public ResourceExists(String resource, Integer id, Boolean exists) {
        super(String.format("El %s con identificador %d %s existe en la base de datos", resource, id,
                exists ? "ya" : "no"));
    }

}