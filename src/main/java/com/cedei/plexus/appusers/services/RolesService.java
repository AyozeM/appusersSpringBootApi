package com.cedei.plexus.appusers.services;

import java.util.List;

import com.cedei.plexus.appusers.db.RoleRepository;
import com.cedei.plexus.appusers.exceptions.java.ResourceExists;
import com.cedei.plexus.appusers.models.Role;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * RolesService
 * 
 * @author Ayoze Martin Hdez
 * @version 0.0.1
 */
@Service
public class RolesService extends ServiceUtils {

    final Logger logger = LoggerFactory.getLogger(RolesService.class);

    @Autowired
    RoleRepository repository;

    /**
     * Construye un nuevo servicio de roles
     */
    public RolesService() {
        this.resource = "rol";
    }

    /**
     * Obtiene todos los roles
     * 
     * @return lista de roles
     * @throws Exception
     */
    public List<Role> getAll() throws Exception {
        List<Role> list = repository.findAll();
        logger.debug("Obteniendo todos los roles");
        if (list == null) {
            throw new Exception("fallo");
        }
        return list;
    }

    /**
     * Añade un nuevo rol
     * 
     * @param toAdd rol a añadir
     * @return rol añadido
     * @throws ResourceExists
     * @throws Exception
     */
    public Role add(Role toAdd) throws ResourceExists, Exception {
        Role toReturn = null;
        logger.debug("añadiendo un rol");
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
     * Obtiene un rol por su identificador
     * 
     * @param id identificador de rol
     * @return rol buscado
     * @throws ResourceExists
     * @throws Exception
     */
    public Role getById(Integer id) throws ResourceExists, Exception {
        Role toReturn = null;
        logger.debug(String.format("Buscando el rol con id %d", id));
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
     * Actualiza un rol existente
     * 
     * @param toUpdate rol con las modificaciones
     * @return rol actualizado
     * @throws ResourceExists
     * @throws Exception
     */
    public Role update(Role toUpdate) throws ResourceExists, Exception {
        Role toReturn = null;
        logger.debug(String.format("Actualizando rol con id %d", toUpdate.getId()));
        try {
            Role dbRole = (Role) this.exists(toUpdate.getId(), true, repository).get();
            dbRole.setName(toUpdate.getName());
            dbRole.setPrivileges(toUpdate.getPrivileges());
            dbRole.setUsers(toUpdate.getUsers());
            toReturn = repository.save(dbRole);
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
     * Elimina un rol
     * 
     * @param id identificador de rol
     * @return mensaje de confirmación
     * @throws ResourceExists
     * @throws Exception
     */
    public String remove(Integer id) throws ResourceExists, Exception {
        try {
            logger.debug(String.format("Eliminando el rol con id %d", id));
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