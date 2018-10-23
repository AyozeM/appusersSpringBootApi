package com.cedei.plexus.appusers.db;

import com.cedei.plexus.appusers.pojo.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository
 * 
 * Respositorio de usuarios
 * 
 * Servirá para almacenar los usuarios de la base de datos
 */
@Repository
 public interface UserRepository  extends JpaRepository<User,Integer>{
    
}