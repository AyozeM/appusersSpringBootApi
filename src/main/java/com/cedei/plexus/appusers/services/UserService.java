package com.cedei.plexus.appusers.services;

import java.util.List;

import com.cedei.plexus.appusers.db.UserRepository;
import com.cedei.plexus.appusers.exceptions.java.ResourceExists;
import com.cedei.plexus.appusers.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService
 * 
 * @author Ayoze Martin Hdez
 * @version 0.0.1
 */
@Service
public class UserService extends ServiceUtils {

    @Autowired
    UserRepository repository;

    /**
     * Construye un nuevo servicio de usuarios
     */
    public UserService() {
        this.resource = "User";
    }

    /**
     * Obtiene todos los usuarios
     * 
     * @return lista de usuarios
     */
    public List<User> getAll() {
        return repository.findAll();
    }

    /**
     * Añade un nuevo usuario
     * 
     * @param toAdd usuario a añadir
     * @return usuario añadido
     * @throws ResourceExists
     * @throws Exception
     */
    public User add(User toAdd) throws ResourceExists, Exception {
        User toReturn = null;
        try {
            this.exists(toAdd.getId(), false, repository);
            toReturn = repository.save(toAdd);
        } catch (ResourceExists e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return toReturn;
    }

    /**
     * Obtiene un usuario por su identificador
     * 
     * @param id identificador de usuario
     * @return usuario buscado
     * @throws ResourceExists
     * @throws Exception
     */
    public User getById(Integer id) throws ResourceExists, Exception {
        User toReturn = null;
        try {
            this.exists(id, true, repository);
            toReturn = repository.findById(id).get();
        } catch (ResourceExists e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return toReturn;
    }

    /**
     * Actualiza un usuario existente
     * 
     * @param toUpdate modificaciones del usuario
     * @return usuario actualizado
     * @throws ResourceExists
     * @throws Exception
     */
    public User update(User toUpdate) throws ResourceExists, Exception {
        User toReturn = null;
        try {
            User dbUser = (User) this.exists(toUpdate.getId(), true, repository).get();
            dbUser.setEmail(toUpdate.getEmail());
            dbUser.setName(toUpdate.getName());
            dbUser.setRoles(toUpdate.getRoles());
            toReturn = repository.save(dbUser);
        } catch (ResourceExists e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return toReturn;
    }

    /**
     * Elimna un usuario
     * 
     * @param id identificador de usuario
     * @return mensaje de confirmacion
     * @throws ResourceExists
     * @throws Exception
     */
    public String remove(Integer id) throws ResourceExists, Exception {
        try {
            this.exists(id, true, repository);
            repository.deleteById(id);
        } catch (ResourceExists e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        return String.format("%s %d has been remove sucessfully", this.resource, id);
    }

}