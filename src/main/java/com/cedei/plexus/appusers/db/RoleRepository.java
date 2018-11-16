package com.cedei.plexus.appusers.db;

import java.util.List;

import com.cedei.plexus.appusers.models.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * RoleRepository
 * 
 * Repositorio de roles
 * 
 * Servirá para almacenar los roles de la base de datos
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("select r.name from Role r where (select p from Privilege p where p.authorization  = ?1) member of r.privileges")
    List<Role> filterByPrivilege(Integer idPrivilege);
}