package com.cedei.plexus.appusers.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

/**
 * User
 * 
 * Modelo para almacenar un usuario
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 564923998964686810L;

    /**
     * Identificador de usuario
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", unique = true, nullable = false)
    protected Integer id_user;

    /**
     * Nombre de usuario
     */
    @Column(name = "name", length = 20)
    protected String name;

    @Column(name = "password", length = 200)
    protected String password;

    /**
     * Email del usuario
     */
    @Column(name = "email", length = 45)
    protected String email;

    /**
     * Lista de roles asociados
     */

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_user", joinColumns = { @JoinColumn(name = "id_user") }, inverseJoinColumns = {
            @JoinColumn(name = "id_role") })
    protected List<Role> roles = new ArrayList<>();

    /**
     * Cosntructor
     */
    public User() {
        // empty
    }

    /**
     * Constructor
     * 
     * @param name  nombre
     * @param email email
     */
    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    /**
     * Getter de id
     * 
     * @return id
     */
    public Integer getId() {
        return this.id_user;
    }

    /**
     * Setter de id
     * 
     * @param id id
     */
    public void setId(Integer id) {
        this.id_user = id;
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
        if (name != null) {
            this.name = name;
        }
    }

    /**
     * Getter de contraseña
     * 
     * @return pasword
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Setter de contraseña
     * 
     * @param password new password
     */
    public void setPassword(String password) {
        if (password != null) {
            this.password = password;
        }
    }

    /**
     * Getter de email
     * 
     * @return email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Setter de email
     * 
     * @param email email
     */
    public void setEmail(String email) {
        if (email != null) {
            this.email = email;
        }
    }

    /**
     * Getter de lista de roles
     * 
     * @return lista de roles
     */
    public List<Role> getRoles() {
        return this.roles;
    }

    /**
     * Getter de lista de roles
     * 
     * @param roles lista de roles
     */
    public void setRoles(List<Role> roles) {
        if (roles == null || roles.size() > 0) {
            this.roles = roles;
        }
    }

}