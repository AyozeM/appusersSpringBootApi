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
    @Query("select r.name from Role r join r.privileges x where x.authorization = ?1")
    List<Role> filterByPrivilege(Integer idPrivilege);
}