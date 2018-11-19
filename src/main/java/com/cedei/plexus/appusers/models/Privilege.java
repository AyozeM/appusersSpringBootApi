package com.cedei.plexus.appusers.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Privilege
 * 
 * Modelo para almacenar un privilegio
 * 
 * @author Ayoze Martin Hdez
 * @version 0.0.1
 */
@Entity
@Table(name = "privilege")
public class Privilege implements Serializable {

    private static final long serialVersionUID = 5698473237370864696L;

    /**
     * Identificador de privilegio
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_privilege", nullable = false, unique = true)
    private Integer id_privilege;

    /**
     * Nombre del privilegio
     */
    @Column(name = "name", length = 20)
    private String name;

    /**
     * Nivel de autorizacion
     */
    @Column(name = "authorization")
    private Integer authorization;

    /**
     * Lista de roles asociados
     */
    @ManyToMany(mappedBy = "privileges")
    @JsonIgnore
    private List<Role> roles = new ArrayList<>();

    /**
     * Constructor por defecto
     */
    public Privilege() {
        // empty
    }

    /**
     * Constructor de un privilegio existente
     * 
     * @param id            identificador de privilegio
     * @param name          nombre del privilegio
     * @param authorization autorizacion
     */
    public Privilege(Integer id, String name, Integer authorization) {
        this.id_privilege = id;
        this.name = name;
        this.authorization = authorization;
    }

    /**
     * Constructor para añadir
     * 
     * @param name          nombre del privilegio
     * @param authorization autorizacion
     */
    public Privilege(String name, Integer authorization) {
        this.name = name;
        this.authorization = authorization;
    }

    /**
     * Getter para id
     * 
     * @return id
     */
    public Integer getId() {
        return this.id_privilege;
    }

    /**
     * Setter para id
     * 
     * @param id id
     */
    public void setId(Integer id) {
        this.id_privilege = id;
    }

    /**
     * Getter para el nombre
     * 
     * @return nombre
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter para el nombre
     * 
     * @param name nombre
     */
    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
    }

    /**
     * Getter para el nivel de autorizacion
     * 
     * @return nivel de autorizacion
     */
    public Integer getAuthorization() {
        return this.authorization;
    }

    /**
     * Setter para el nivel de autorizacion
     * 
     * @param authorization nivel de autorizacion
     */
    public void setAuthorization(Integer authorization) {
        this.authorization = authorization;
    }

    /**
     * Getter para roles
     * 
     * @return roles
     */
    public List<Role> getRoles() {
        return this.roles;
    }

    /**
     * Setter para roles
     * 
     * @param roles roles
     */
    public void setRoles(List<Role> roles) {
        if (roles == null || roles.size() > 0) {
            this.roles = roles;
        }
    }
}