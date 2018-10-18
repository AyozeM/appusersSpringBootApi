package com.cedei.plexus.appusers.db;

import com.cedei.plexus.appusers.pojo.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * RoleRepository
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    
}