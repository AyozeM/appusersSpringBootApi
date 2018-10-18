package com.cedei.plexus.appusers.controllers;

import java.util.List;

import com.cedei.plexus.appusers.db.PrivilegeRepository;
import com.cedei.plexus.appusers.db.RoleRepository;
import com.cedei.plexus.appusers.db.UserRepository;
import com.cedei.plexus.appusers.exceptions.BadRequestException;
import com.cedei.plexus.appusers.exceptions.ConfictExeption;
import com.cedei.plexus.appusers.exceptions.NotFoundExeption;
import com.cedei.plexus.appusers.pojo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    public ResponseEntity<?> addUser(RequestEntity<User> user) {
        ResponseEntity<?> response = null;
        User newUser = user.getBody();
        if(newUser == null){
            response = new ResponseEntity<BadRequestException>(new BadRequestException("Formato de petición inválido"), HttpStatus.BAD_REQUEST);
        }else{
            if(newUser.getId() != null && userRepository.findById(newUser.getId()).isPresent()){
                response = new ResponseEntity<ConfictExeption>(new ConfictExeption("Este usuario ya existia previamente"),HttpStatus.CONFLICT);
            }else{
                response = new ResponseEntity<User>(userRepository.save(newUser),HttpStatus.OK);
            }
        }
        return response;
    }
    
}