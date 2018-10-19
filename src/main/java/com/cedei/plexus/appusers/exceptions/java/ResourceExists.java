package com.cedei.plexus.appusers.exceptions.java;

/**
 * ResourceExists
 */
public class ResourceExists  extends Exception{

    private static final long serialVersionUID = 1L;

    public ResourceExists(String resource,Integer id, Boolean exists) {
        super(String.format("El %s con identificador %d %s existe en la base de datos", resource, id, exists?"ya":"no"));
    }

    
}