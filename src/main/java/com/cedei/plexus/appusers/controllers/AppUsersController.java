package com.cedei.plexus.appusers.controllers;

import java.util.List;

import com.cedei.plexus.appusers.db.PrivilegeRepository;
import com.cedei.plexus.appusers.db.RoleRepository;
import com.cedei.plexus.appusers.db.UserRepository;
import com.cedei.plexus.appusers.exceptions.NotFoundExeption;
import com.cedei.plexus.appusers.pojo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * AppUsersController
 */
@RestController
@RequestMapping("api")
public class AppUsersController {

    @Autowired
    PrivilegeRepository privilegeRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        ResponseEntity<?> response;
        List<User> result = userRepository.findAll();
        if(result != null){
            response = new ResponseEntity<List<User>>(result,HttpStatus.OK);
        }else{
            response = new ResponseEntity<NotFoundExeption>(new NotFoundExeption("No se obtuvieron los usuarios"), HttpStatus.NOT_FOUND);
        }
        return response;
    }
    
    @PostMapping("/user")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        ResponseEntity<?> response = null;

        return response;
    }
    
}