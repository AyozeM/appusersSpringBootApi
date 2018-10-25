package com.cedei.plexus.appusers.controllers;

import java.util.List;

import com.cedei.plexus.appusers.exceptions.java.EmptyBodyException;
import com.cedei.plexus.appusers.exceptions.java.ResourceExists;
import com.cedei.plexus.appusers.exceptions.rest.BadRequestException;
import com.cedei.plexus.appusers.exceptions.rest.ConfictExeption;
import com.cedei.plexus.appusers.exceptions.rest.NotFoundExeption;
import com.cedei.plexus.appusers.models.Role;
import com.cedei.plexus.appusers.services.RolesService;

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
 * RolesController
 * 
 * Controlador de roles
 * 
 * @author Ayoze Martin Hdez
 * @version 0.0.1
 */
@RestController
@RequestMapping("/api/roles")
@Api(value = "/api/roles", description = "Operaciones robre roles")
public class RolesController extends Controller implements ControllerInterface {

    @Autowired
    RolesService service;

    /**
     * Constructor:
     * 
     * Crea un nuevo controlador de roles
     */
    public RolesController() {
        this.resource = "rol";
    }

    /**
     * Implementacion del metodo add
     */
    @Override
    @PostMapping("add")
    @ApiOperation(value = "Añade un nuevo rol", response = Role.class)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Rol añadido correctamente"),
            @ApiResponse(code = 409, message = "El rol con el id especificado ya existe"),
            @ApiResponse(code = 400, message = "El cuerpo de la petición es inválido") })
    public ResponseEntity<?> add(RequestEntity<?> request) {
        ResponseEntity<?> response;
        try {
            checkEmptyBody(request.getBody());
            Role toAdd = converter.convertValue(request.getBody(), Role.class);
            response = new ResponseEntity<Role>(this.service.add(toAdd), HttpStatus.CREATED);
        } catch (EmptyBodyException e) {
            response = new ResponseEntity<BadRequestException>(new BadRequestException(), HttpStatus.BAD_REQUEST);
        } catch (ResourceExists e) {
            response = new ResponseEntity<ConfictExeption>(new ConfictExeption(e.getMessage()), HttpStatus.CONFLICT);
        } catch (Exception e) {
            response = new ResponseEntity<String>("fallo inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    /**
     * Implementacion del metodo getAll
     */
    @Override
    @GetMapping("all")
    @ApiOperation(value = "Obtiene todos los roles registrado", response = Role.class, responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Éxito al obtener los roles"),
            @ApiResponse(code = 500, message = "Fallo con la base de datos") })
    public ResponseEntity<?> getAll(RequestEntity<?> request) {
        ResponseEntity<?> response;
        try {
            response = new ResponseEntity<List<Role>>(this.service.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseEntity<String>("Hubo un fallo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    /**
     * Implementacion del metodo findById
     */
    @Override
    @GetMapping("show/{id}")
    @ApiOperation(value = "Obtiene un rol por su id", response = Role.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Rol encontrado"),
            @ApiResponse(code = 404, message = "Rol no encontrado"), })
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        ResponseEntity<?> response;
        try {
            response = new ResponseEntity<Role>(this.service.getById(id), HttpStatus.OK);
        } catch (ResourceExists e) {
            response = new ResponseEntity<NotFoundExeption>(new NotFoundExeption(this.resource, id),
                    HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response = new ResponseEntity<String>("Fallo inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    /**
     * Implementacion del metodo update
     */
    @Override
    @PutMapping("update")
    @ApiOperation(value = "Actualiza un rol", response = Role.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Rol actualizado correctamente"),
            @ApiResponse(code = 400, message = "Cuerpo de la petición inválido"),
            @ApiResponse(code = 404, message = "El rol especificado no existe") })
    public ResponseEntity<?> update(RequestEntity<?> request) {
        ResponseEntity<?> response;
        Role toUpdate = null;
        try {
            checkEmptyBody(request.getBody());
            toUpdate = converter.convertValue(request.getBody(), Role.class);
            response = new ResponseEntity<Role>(this.service.update(toUpdate), HttpStatus.OK);
        } catch (EmptyBodyException e) {
            response = new ResponseEntity<BadRequestException>(new BadRequestException(), HttpStatus.BAD_REQUEST);
        } catch (ResourceExists e) {
            response = new ResponseEntity<ResourceExists>(new ResourceExists(this.resource, toUpdate.getId(), false),
                    HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response = new ResponseEntity<String>("fallo inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    /**
     * Implementacion del metodo remove
     */
    @Override
    @DeleteMapping("remove/{id}")
    @ApiOperation(value = "Elimina un rol", response = Role.class)
    @ApiResponses(value = { @ApiResponse(code = 404, message = "El rol especificado no existe"),
            @ApiResponse(code = 200, message = "Rol eliminado correctamente") })
    public ResponseEntity<?> remove(@PathVariable Integer id) {
        ResponseEntity<?> response;
        try {
            response = new ResponseEntity<String>(this.service.remove(id), HttpStatus.OK);
        } catch (ResourceExists e) {
            response = new ResponseEntity<NotFoundExeption>(new NotFoundExeption(this.resource, id),
                    HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response = new ResponseEntity<String>("fallo inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

}