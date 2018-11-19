package com.cedei.plexus.appusers.services;

import java.util.List;

import com.cedei.plexus.appusers.db.UserRepository;
import com.cedei.plexus.appusers.exceptions.java.ResourceExists;
import com.cedei.plexus.appusers.models.Privilege;
import com.cedei.plexus.appusers.models.Role;
import com.cedei.plexus.appusers.models.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * UserService
 * 
 * @author Ayoze Martin Hdez
 * @version 0.0.1
 */
@Service
public class UserService extends ServiceUtils {

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    
    @Autowired
    UserRepository repository;

    final Logger logger = LoggerFactory.getLogger(ServiceUtils.class);

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
    public List<User> getAll() throws Exception {
        List<User> list = repository.findAll();
        logger.debug("Obteniendo todos los usuarios");
        if (list == null) {
            throw new Exception();
        }
        return list;
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
        logger.debug("Añadiendo un usuario");
        try {
            this.exists(toAdd.getId(), false, repository);
            toAdd.setPassword(this.bCryptPasswordEncoder.encode(toAdd.getPassword()));
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
        logger.debug(String.format("Buscando usuario con id %d",id));
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
        logger.debug(String.format("Actualizando usuario con id %d", toUpdate.getId()));
        try {
            User dbUser = (User) this.exists(toUpdate.getId(), true, repository).get();
            dbUser.setEmail(toUpdate.getEmail());
            dbUser.setName(toUpdate.getName());
            dbUser.setRoles(toUpdate.getRoles());
            dbUser.setPassword(this.bCryptPasswordEncoder.encode(toUpdate.getPassword()));
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
        logger.debug(String.format("Eliminando usaurio con id %d", id));
        this.exists(id, true, repository);
        repository.deleteById(id);
        return String.format("%s %d has been remove sucessfully", this.resource, id);
    }

    public Integer getAuthority(String name){
        User aux = repository.findByEmail(name);
        //User aux = repository.findByName(name);
        Integer sumPrivi = 0;
        for (Role role : aux.getRoles()) {
            for (Privilege privilege : role.getPrivileges()) {
                Integer actualAuth = privilege.getAuthorization();
                if (actualAuth > sumPrivi) {
                    sumPrivi = actualAuth;
                }
            }
        }
        return sumPrivi;
    }

}