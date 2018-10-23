package com.cedei.plexus.appusers.db;

import com.cedei.plexus.appusers.pojo.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * RoleRepository
 * 
 * Repositorio de roles
 * 
 * Servirá para almacenar los roles de la base de datos
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    
}