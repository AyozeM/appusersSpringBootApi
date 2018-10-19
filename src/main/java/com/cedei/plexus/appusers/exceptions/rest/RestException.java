package com.cedei.plexus.appusers.exceptions.rest;

import java.util.Date;

import org.springframework.http.HttpStatus;

/**
 * RestException
 */
public class RestException {

    protected String message;
    protected HttpStatus status;
    protected Date date;

    public RestException(HttpStatus status) {
        this.status = status;
        this.date = new Date();
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return this.status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public RestException message(String message) {
        this.message = message;
        return this;
    }

    public RestException state(HttpStatus state) {
        this.status = state;
        return this;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}