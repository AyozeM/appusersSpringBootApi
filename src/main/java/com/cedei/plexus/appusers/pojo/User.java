package com.cedei.plexus.appusers.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * User
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", unique = true, nullable = false)
    private Integer id_user;

    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "email", length = 45)
    private String email;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_role"))
    private List<Role> roles = new ArrayList<>();

    public User() {
    }

    public User(Integer id, String name, String email) {
        this.id_user = id;
        this.name = name;
        this.email = email;
    }

    public Integer getId() {
        return this.id_user;
    }

    public void setId(Integer id) {
        this.id_user = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}