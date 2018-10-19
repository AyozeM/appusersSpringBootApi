package com.cedei.plexus.appusers.controllers;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

/**
 * controllerInterface
 */
public interface ControllerInterface {

    public ResponseEntity<?> add(RequestEntity<?> request);

    public ResponseEntity<?> getAll(RequestEntity<?> request);

    public ResponseEntity<?> find(RequestEntity<?> request);

    public ResponseEntity<?> update(RequestEntity<?> request);

    public ResponseEntity<?> remove(Integer id);

}