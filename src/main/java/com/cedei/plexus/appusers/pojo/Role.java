package com.cedei.plexus.appusers.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Role
 */
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role", nullable = false, unique = true)
    private Integer id_role;
    @Column(name = "name", length = 20)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();

    @ManyToMany(mappedBy = "roles")
    private List<Privilege> privileges = new ArrayList<>();

    public Role() {
    }

    public Role(Integer id, String name) {
        this.id_role = id;
        this.name = name;
    }

    public Integer getId() {
        return this.id_role;
    }

    public void setId(Integer id) {
        this.id_role = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Privilege> getPrivileges() {
        return this.privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }
}