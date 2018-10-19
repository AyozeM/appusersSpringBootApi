package com.cedei.plexus.appusers.controllers;

import java.util.List;
import java.util.Optional;

import com.cedei.plexus.appusers.db.UserRepository;
import com.cedei.plexus.appusers.exceptions.java.EmptyBodyException;
import com.cedei.plexus.appusers.exceptions.java.UserExistException;
import com.cedei.plexus.appusers.exceptions.rest.BadRequestException;
import com.cedei.plexus.appusers.exceptions.rest.ConfictExeption;
import com.cedei.plexus.appusers.exceptions.rest.NotFoundExeption;
import com.cedei.plexus.appusers.pojo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AppUsersController
 */
@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        ResponseEntity<?> response;
        List<User> result = userRepository.findAll();
        if (result != null) {
            response = new ResponseEntity<List<User>>(result, HttpStatus.OK);
        } else {
            response = new ResponseEntity<NotFoundExeption>(new NotFoundExeption("No se obtuvieron los usuarios"),
                    HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PostMapping("/user")
    public ResponseEntity<?> addUser(RequestEntity<User> user) {
        ResponseEntity<?> response = null;
        User toAdd = user.getBody();
        try {
            checkEmptyBody(toAdd);
            existsUser(toAdd, false);
            response = new ResponseEntity<User>(userRepository.save(toAdd), HttpStatus.OK);
        } catch (EmptyBodyException e) {
            System.out.println(e.getMessage());
            response = new ResponseEntity<BadRequestException>(new BadRequestException(), HttpStatus.BAD_REQUEST);
        } catch (UserExistException e) {
            System.out.println(e.getMessage());
            response = new ResponseEntity<ConfictExeption>(new ConfictExeption(e.getMessage()), HttpStatus.CONFLICT);
        }
        return response;
    }

    @PutMapping("/user")
    public ResponseEntity<?> updateUser(RequestEntity<User> user) {
        ResponseEntity<?> response;
        User toUpdate = user.getBody();
        try {
            checkEmptyBody(toUpdate);
            existsUser(toUpdate,true);
            response = new ResponseEntity<User>(userRepository.save(toUpdate), HttpStatus.OK);
        } catch (EmptyBodyException e) {
            System.out.println(e.getMessage());
            response = new ResponseEntity<BadRequestException>(new BadRequestException(), HttpStatus.BAD_REQUEST);
        } catch (UserExistException e) {
            System.out.println(e.getMessage());
            response = new ResponseEntity<ConfictExeption>(new ConfictExeption(e.getMessage()), HttpStatus.CONFLICT);
        }
        return response;
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> removeUser(@PathVariable Integer id) {
        ResponseEntity<?> response;
        try {
            User toRemove = existsUser(id, true);
            userRepository.deleteById(id);
            response = new ResponseEntity<User>(toRemove, HttpStatus.OK);
        } catch (UserExistException e) {
            System.out.println(e.getMessage());
            response = new ResponseEntity<NotFoundExeption>(new NotFoundExeption(e.getMessage()), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            System.out.println(e);
            response = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    private void checkEmptyBody(User toCheck) throws EmptyBodyException {
        if (toCheck == null) {
            throw new EmptyBodyException();
        }
    }

    private void existsUser(User toCheck, Boolean mustExist) throws UserExistException{
        Boolean result = toCheck.getId() != null && userRepository.findById(toCheck.getId()).isPresent();
        if(result && !mustExist){
            throw new UserExistException(toCheck.getId(), true);
        }
        
        if(!result && mustExist){
            throw new UserExistException(toCheck.getId(), false);
        }
    }
    
    private User existsUser(Integer id, Boolean mustExist) throws UserExistException{
        User response;
        try {
            response = userRepository.findById(id).get();
            this.existsUser(response, mustExist);
        } catch (Exception e) {
            throw new UserExistException(id, false);
        }
        return response;
    }
}