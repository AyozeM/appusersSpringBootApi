package com.cedei.plexus.appusers.controllers;

import java.util.List;

import com.cedei.plexus.appusers.exceptions.java.EmptyBodyException;
import com.cedei.plexus.appusers.exceptions.java.ResourceExists;
import com.cedei.plexus.appusers.exceptions.rest.BadRequestException;
import com.cedei.plexus.appusers.exceptions.rest.ConfictExeption;
import com.cedei.plexus.appusers.exceptions.rest.NotFoundExeption;
import com.cedei.plexus.appusers.models.User;
import com.cedei.plexus.appusers.services.UserService;

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

    @Autowired
    UserService service;

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
            response = new ResponseEntity<User>(this.service.add(toAdd), HttpStatus.OK);
        } catch (EmptyBodyException e) {
            response = new ResponseEntity<BadRequestException>(new BadRequestException(), HttpStatus.BAD_REQUEST);
        } catch (ResourceExists e) {
            response = new ResponseEntity<ConfictExeption>(new ConfictExeption(e.getMessage()), HttpStatus.CONFLICT);
        } catch (Exception e) {
            response = new ResponseEntity<String>("Fallo interno", HttpStatus.INTERNAL_SERVER_ERROR);
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
        try {
            response = new ResponseEntity<List<User>>(this.service.getAll(),HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<String>("Fallo interno", HttpStatus.INTERNAL_SERVER_ERROR);
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
            response = new ResponseEntity<User>(service.getById(id), HttpStatus.OK);
        } catch (ResourceExists e) {
            response = new ResponseEntity<NotFoundExeption>(new NotFoundExeption(this.resource, id),
                    HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response = new ResponseEntity<String>("fallo interno", HttpStatus.INTERNAL_SERVER_ERROR);
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
        User sendUser = null;
        try {
            checkEmptyBody(request.getBody());
            sendUser = converter.convertValue(request.getBody(), User.class);
            response = new ResponseEntity<User>(this.service.update(sendUser), HttpStatus.OK);
        } catch (EmptyBodyException e) {
            response = new ResponseEntity<BadRequestException>(new BadRequestException(), HttpStatus.BAD_REQUEST);
        } catch (ResourceExists e) {
            response = new ResponseEntity<NotFoundExeption>(new NotFoundExeption(this.resource, sendUser.getId()),
                    HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response = new ResponseEntity<String>("Fallo inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
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
    public ResponseEntity<?> remove(@PathVariable Integer id) {
        ResponseEntity<?> response;
        try {
            response = new ResponseEntity<String>(service.remove(id), HttpStatus.OK);
        } catch (ResourceExists e) {
            e.printStackTrace();
            response = new ResponseEntity<NotFoundExeption>(new NotFoundExeption(this.resource, id),
                    HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseEntity<String>("fallo inesparado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

}