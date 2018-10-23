package com.cedei.plexus.appusers.controllers;

import java.util.List;

import com.cedei.plexus.appusers.db.PrivilegeRepository;
import com.cedei.plexus.appusers.exceptions.java.EmptyBodyException;
import com.cedei.plexus.appusers.exceptions.java.ResourceExists;
import com.cedei.plexus.appusers.exceptions.rest.BadRequestException;
import com.cedei.plexus.appusers.exceptions.rest.ConfictExeption;
import com.cedei.plexus.appusers.exceptions.rest.NotFoundExeption;
import com.cedei.plexus.appusers.pojo.Privilege;

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
 * PrivilegesController
 * 
 * Controlador de privilegios
 * 
 * @author Ayoze Martin Hdez
 * @version 0.0.1
 */
@RestController
@RequestMapping("/api/privileges")
@Api(value = "/api/privileges", description = "Operaciones sobre privilegios")
public class PrivilegesController extends Controller implements ControllerInterface {

    /**
     * repositorio de privilegios
     */
    @Autowired
    PrivilegeRepository repository;

    public PrivilegesController() {
        this.resource = "privilege";
    }

    /**
     * Implementación del metodo add
     */
    @Override
    @PostMapping("add")
    @ApiOperation(value = "Añade un nuevo privilegio", response = Privilege.class)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Privilegio añadido correctamente"),
            @ApiResponse(code = 409, message = "El usuario con el id especificado ya existe previamente") })
    public ResponseEntity<?> add(RequestEntity<?> request) {
        ResponseEntity<?> response;
        try {
            checkEmptyBody(request.getBody());
            Privilege toAdd = converter.convertValue(request.getBody(), Privilege.class);
            exists(toAdd.getId(), false, repository);
            response = new ResponseEntity<Privilege>(repository.save(toAdd), HttpStatus.CREATED);
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
     * Implementacio del metodo getAll
     */
    @Override
    @GetMapping("all")
    @ApiOperation(value = "Obtiene todos los usuarios almacenados", response = Privilege.class, responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Hubo un problema con la base de datos") })
    public ResponseEntity<?> getAll(RequestEntity<?> request) {
        ResponseEntity<?> response;
        List<Privilege> results = repository.findAll();
        if (results != null) {
            response = new ResponseEntity<List<Privilege>>(results, HttpStatus.OK);
        } else {
            response = new ResponseEntity<String>("Hubo un fallo al obtener los privilegios",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    /**
     * Implementación del metodo findById
     */
    @Override
    @GetMapping("show/{id}")
    @ApiOperation(value = "Obtiene un privilegio por su id", response = Privilege.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Privilegio encontrado"),
            @ApiResponse(code = 404, message = "Privilegio no encontrado"), })
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        ResponseEntity<?> response;
        try {
            exists(id, true, repository);
            response = new ResponseEntity<Privilege>(repository.findById(id).get(), HttpStatus.OK);
        } catch (ResourceExists e) {
            e.printStackTrace();
            response = new ResponseEntity<NotFoundExeption>(new NotFoundExeption(this.resource, id),
                    HttpStatus.NOT_FOUND);
        }
        return response;
    }

    /**
     * Implementación del metodo update
     */
    @Override
    @PutMapping("update")
    @ApiOperation(value = "Actualiza un privilegio existente", response = Privilege.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Privilegio actualizado"),
            @ApiResponse(code = 400, message = "Cuerpo de la petición inválido"),
            @ApiResponse(code = 404, message = "El privilegio especificado no existe") })
    public ResponseEntity<?> update(RequestEntity<?> request) {
        ResponseEntity<?> response;
        Privilege toUpdate = null;
        try {
            checkEmptyBody(request.getBody());
            toUpdate = converter.convertValue(request.getBody(), Privilege.class);
            exists(toUpdate.getId(), true, repository);
            response = new ResponseEntity<Privilege>(repository.save(toUpdate), HttpStatus.OK);
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
     * Implementación del metodo remove
     */
    @Override
    @DeleteMapping("remove/{id}")
    @ApiOperation(value = "Elimina un privilegio", response = Privilege.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "El privilegio fue eliminado correctamente"),
            @ApiResponse(code = 404, message = "El privilegio especificado no existe") })
    public ResponseEntity<?> remove(@PathVariable Integer id) {
        ResponseEntity<?> response;
        try {
            exists(id, true, repository);
            repository.deleteById(id);
            response = new ResponseEntity<String>(String.format("Privilege %d has been remove sucessfully", id),
                    HttpStatus.OK);
        } catch (ResourceExists e) {
            e.printStackTrace();
            response = new ResponseEntity<NotFoundExeption>(new NotFoundExeption(this.resource, id),
                    HttpStatus.NOT_FOUND);
        }
        return response;
    }

}