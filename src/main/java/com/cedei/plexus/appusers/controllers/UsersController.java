package com.cedei.plexus.appusers.controllers;

import java.util.List;

import com.cedei.plexus.appusers.db.UserRepository;
import com.cedei.plexus.appusers.exceptions.java.EmptyBodyException;
import com.cedei.plexus.appusers.exceptions.java.ResourceExists;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * UsersController
 * 
 * Cotrolador de usuarios
 * 
 * @author Ayoze Martin Hdez
 * @version 0.0.1
 */
@RestController
@RequestMapping("/api/users")
@Api(value = "/api/users", description = "Operaciones sobre usuarios", consumes = "application/json")
public class UsersController extends Controller implements ControllerInterface {

    /**
     * Repositorio de usuarios
     */
    @Autowired
    UserRepository repository;

    /**
     * Constructor
     */
    public UsersController() {
        this.resource = "usuario";
    }

    /**
     * Implementacion del metodo add
     */
    @Override
    @PostMapping("add")
    @ApiOperation(value = "Añade un nuevo usuario", response = User.class)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Usuario añadido correctamente"),
            @ApiResponse(code = 400, message = "Cuerpo de la petición inválido"),
            @ApiResponse(code = 409, message = "El usuario con el id especificado ya existe en la base de datos"), })
    public ResponseEntity<?> add(RequestEntity<?> request) {
        ResponseEntity<?> response;
        try {
            checkEmptyBody(request.getBody());
            User toAdd = converter.convertValue(request.getBody(), User.class);
            exists(toAdd.getId(), false, repository);
            response = new ResponseEntity<User>(repository.save(toAdd), HttpStatus.OK);
        } catch (EmptyBodyException e) {
            e.printStackTrace();
            response = new ResponseEntity<BadRequestException>(new BadRequestException(), HttpStatus.BAD_REQUEST);
        } catch (ResourceExists e) {
            e.printStackTrace();
            response = new ResponseEntity<ConfictExeption>(new ConfictExeption(e.getMessage()), HttpStatus.CONFLICT);
        }
        return response;
    }

    /**
     * Implementacion del metodo getAll
     */
    @Override
    @GetMapping("all")
    @ApiOperation(value = "Obtiene todos los usuarios", response = User.class, responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Usuarios obtenidos"),
            @ApiResponse(code = 500, message = "Fallo en la base de datos"), })
    public ResponseEntity<?> getAll(RequestEntity<?> request) {
        ResponseEntity<?> response;
        List<User> result = repository.findAll();
        if (result != null) {
            response = new ResponseEntity<List<User>>(result, HttpStatus.OK);
        } else {
            response = new ResponseEntity<String>("Hubo un fallo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    /**
     * Implementacion del metodo findById
     */
    @Override
    @GetMapping("show/{id}")
    @ApiOperation(value = "Obtiene un usuario por su id", response = User.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Usuario encontrado"),
            @ApiResponse(code = 404, message = "Usuario no encontrado"), })
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        ResponseEntity<?> response;
        try {
            exists(id, true, repository);
            response = new ResponseEntity<User>(repository.findById(id).get(), HttpStatus.OK);
        } catch (ResourceExists e) {
            e.printStackTrace();
            response = new ResponseEntity<NotFoundExeption>(new NotFoundExeption(this.resource, id),
                    HttpStatus.NOT_FOUND);
        }
        return response;
    }

    /**
     * Implementacion del metodo update
     */
    @Override
    @PutMapping("update")
    @ApiOperation(value = "Actualiza un usuario", response = User.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Usuario actualizado correctamente"),
            @ApiResponse(code = 400, message = "Cuerpo de la petición inválido"),
            @ApiResponse(code = 404, message = "El usuario especificado no existe"), })
    public ResponseEntity<?> update(RequestEntity<?> request) {
        ResponseEntity<?> response;
        User toUpdate = null;
        try {
            checkEmptyBody(request.getBody());
            toUpdate = converter.convertValue(request.getBody(), User.class);
            exists(toUpdate.getId(), true, repository);
            response = new ResponseEntity<User>(repository.save(toUpdate), HttpStatus.OK);
        } catch (EmptyBodyException e) {
            e.printStackTrace();
            response = new ResponseEntity<BadRequestException>(new BadRequestException(), HttpStatus.BAD_REQUEST);
        } catch (ResourceExists e) {
            e.printStackTrace();
            response = new ResponseEntity<ResourceExists>(new ResourceExists(this.resource, toUpdate.getId(), false),
                    HttpStatus.NOT_FOUND);
        }
        return response;
    }

    /**
     * Implementacion del metodo remove
     */
    @Override
    @DeleteMapping("remove/{id}")
    @ApiOperation(value = "Elimina un usuario", response = User.class)
    @ApiResponses(value = { @ApiResponse(code = 404, message = "El usuario especificado no existe"),
            @ApiResponse(code = 200, message = "Usuario eliminado correctamente"), })
    public ResponseEntity<?> remove(Integer id) {
        ResponseEntity<?> response;
        try {
            exists(id, true, repository);
            repository.deleteById(id);
            response = new ResponseEntity<String>(String.format("User %d has been remove  sucessfully", id),
                    HttpStatus.OK);
        } catch (ResourceExists e) {
            e.printStackTrace();
            response = new ResponseEntity<NotFoundExeption>(new NotFoundExeption(this.resource, id),
                    HttpStatus.NOT_FOUND);
        }
        return response;
    }

}