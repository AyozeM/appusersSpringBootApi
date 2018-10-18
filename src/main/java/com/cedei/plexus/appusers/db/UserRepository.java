package com.cedei.plexus.appusers.db;

import com.cedei.plexus.appusers.pojo.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository
 */
@Repository
 public interface UserRepository  extends JpaRepository<User,Integer>{
    
}