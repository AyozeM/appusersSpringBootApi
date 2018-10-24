package com.cedei.plexus.appusers.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Role
 * 
 * Modelo para almacenar un rol
 * 
 * @author Ayoze Martin Hdez
 * @version 0.0.1
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1534486692388823749L;

    /**
     * Identificador de rol
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role", nullable = false, unique = true)
    private Integer id_role;

    /**
     * Nombre del rol
     */
    @Column(name = "name", length = 20)
    private String name;

    /**
     * Lista de usuarios asociados al rol
     */
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    /**
     * Lista de privilegios asociados al rol
     */
    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinTable(name = "role_privilege", joinColumns = @JoinColumn(name = "id_role"), inverseJoinColumns = @JoinColumn(name = "id_privilege"))
    private List<Privilege> privileges = new ArrayList<>();

    /**
     * Constructor
     */
    public Role() {
        // empty
    }

    /**
     * Constructor
     * 
     * @param id   identificador de rol
     * @param name nombre del rol
     */
    public Role(Integer id, String name) {
        this.id_role = id;
        this.name = name;
    }

    /**
     * Getter de id
     * 
     * @return id
     */
    public Integer getId() {
        return this.id_role;
    }

    /**
     * Setter de id
     * 
     * @param id id
     */
    public void setId(Integer id) {
        this.id_role = id;
    }

    /**
     * Getter de nombre
     * 
     * @return nombre
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter de nombre
     * 
     * @param name nombre
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter de lista de usuarios
     * 
     * @return lista de usuarios
     */
    public List<User> getUsers() {
        return this.users;
    }

    /**
     * Setter de lista de usuarios
     * 
     * @param users lista de usuarios
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * Getter de lista de privilegios
     * 
     * @return lista de privilegios
     */
    public List<Privilege> getPrivileges() {
        return this.privileges;
    }

    /**
     * Setter de lista de privilegios
     * 
     * @param privileges lista de privilegios
     */
    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }
}