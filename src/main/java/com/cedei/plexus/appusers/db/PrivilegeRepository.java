package com.cedei.plexus.appusers.db;

import com.cedei.plexus.appusers.pojo.Privilege;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PrivilegeRepository
 */
@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege,Integer> {
    
}