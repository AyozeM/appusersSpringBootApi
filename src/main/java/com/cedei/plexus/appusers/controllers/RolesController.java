package com.cedei.plexus.appusers.controllers;

import java.util.List;
import java.util.Optional;

import com.cedei.plexus.appusers.db.RoleRepository;
import com.cedei.plexus.appusers.exceptions.java.EmptyBodyException;
import com.cedei.plexus.appusers.pojo.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RolesController
 */
@RestController
@RequestMapping("/api/roles")
public class RolesController extends Controller implements ControllerInterface {

    @Autowired
    RoleRepository repository;


    public RolesController() {
        this.resource = "rol";
    }


    @Override
    public ResponseEntity<?> add(RequestEntity<?> request) {
        //TODO: ver si funciona la herencia
        ResponseEntity<?> response;
        try {
            checkEmptyBody(request.getBody());
            Role newRole = repository.findById(id)
            exists(id, false, repository);
        } catch (EmptyBodyException e) {
            //TODO: handle exception
        }
        return response;
    }
    
    @Override
    @GetMapping("roles")
    public ResponseEntity<?> getAll(RequestEntity<?> request) {
        ResponseEntity<?> response;
        /* List<Role> result = repository.findAll(); */
        Object result = this.x(repository);
        if(result != null){
            response = new ResponseEntity<Object>(result,HttpStatus.OK);
        }else{
            response = new ResponseEntity<String>("Hubo un fallo",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @Override
    public ResponseEntity<?> find(RequestEntity<?> request) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(RequestEntity<?> request) {
        return null;
    }

    @Override
    public ResponseEntity<?> remove(Integer id) {
		return null;
	}


    
}