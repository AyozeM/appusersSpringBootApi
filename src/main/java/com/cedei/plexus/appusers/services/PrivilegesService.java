package com.cedei.plexus.appusers.services;

import java.util.List;

import com.cedei.plexus.appusers.db.PrivilegeRepository;
import com.cedei.plexus.appusers.exceptions.java.ResourceExists;
import com.cedei.plexus.appusers.models.Privilege;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PrivilegesService
 * 
 * @author Ayoze Martin Hdez
 * @version 0.0.1
 */
@Service
public class PrivilegesService extends ServiceUtils {

    @Autowired
    PrivilegeRepository repository;

    final Logger logger = LoggerFactory.getLogger(PrivilegesService.class);

    public PrivilegesService() {
        this.resource = "privilege";
    }

    /**
     * Obtiene todos los privilegios
     * 
     * @return lista de privilegios
     * @throws Exception
     */
    public List<Privilege> getAll() throws Exception {
        logger.debug("Obteniendo todos los privilegios");
        List<Privilege> list = this.repository.findAll();
        if (list == null) {
            throw new Exception();
        }
        return list;
    }

    /**
     * añade un nuevo privilegio
     * 
     * @param toAdd privilegio a añadir
     * @return privilegio añadido
     * @throws ResourceExists
     * @throws Exception
     */
    public Privilege add(Privilege toAdd) throws ResourceExists, Exception {
        Privilege toReturn = null;
        logger.debug("Añadiendo un privilegio");
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
     * @param id identificador de privilegio
     * @return privilegio buscado
     * @throws ResourceExists
     * @throws Exception
     */
    public Privilege getById(Integer id) throws ResourceExists, Exception {
        Privilege toReturn = null;
        logger.debug(String.format("Buscando el privilegio con id %d",id));
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
     * Actualiza un privilegio
     * 
     * @param toUpdate privilegio con las modificaciones
     * @return privilegio actualizado
     * @throws ResourceExists
     * @throws Exception
     */
    public Privilege update(Privilege toUpdate) throws ResourceExists, Exception {
        Privilege toReturn = null;
        logger.debug(String.format("Actualizando privilegio con id %d",toUpdate.getId()));
        try {
            Privilege dbPrivilege = (Privilege) this.exists(toUpdate.getId(), true, repository).get();
            dbPrivilege.setName(toUpdate.getName());
            dbPrivilege.setRoles(toUpdate.getRoles());
            toReturn = repository.save(dbPrivilege);
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
     * Elimina un privilegio
     * 
     * @param id identificador de privilegio
     * @return mensaje confirmando su eliminación
     * @throws ResourceExists
     * @throws Exception
     */
    public String remove(Integer id) throws ResourceExists, Exception {
        try {
            logger.debug(String.format("Eliminando privilegio con id %d", id));
            this.exists(id, true, repository);
            repository.deleteById(id);
        } catch (ResourceExists e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return String.format("%s %d has been remove sucessfully", this.resource, id);
    }
}