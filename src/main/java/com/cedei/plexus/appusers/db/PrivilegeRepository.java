package com.cedei.plexus.appusers.db;

import com.cedei.plexus.appusers.models.Privilege;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PrivilegeRepository
 * 
 * Repositorio de privilegios
 * 
 * Servirá para almacenar los privilegios de la base de datos
 */
@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege,Integer> {
    
}