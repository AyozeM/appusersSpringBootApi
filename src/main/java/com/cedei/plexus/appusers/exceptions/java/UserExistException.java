package com.cedei.plexus.appusers.exceptions.java;

/**
 * UserExistException
 */
public class UserExistException extends Exception{

    private static final long serialVersionUID = 1L;

    public UserExistException(Integer id, Boolean exists) {
        super(String.format(new StringBuilder("El usuario con identificador %d ").append(exists?"ya":"no").append(" en la base de datos").toString(),id));
    }
    
}