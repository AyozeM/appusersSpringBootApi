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
 * Privilege
 */
@Entity
@Table(name = "privilege")
 public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_privilege", nullable = false, unique =  true)
    private Integer id_privilege;

    @Column(name = "name", length = 20)
    private String name;
    
    
    @ManyToMany
    @JoinTable(name="role_privilege", joinColumns = @JoinColumn(name = "id_privilege"), inverseJoinColumns = @JoinColumn(name = "id_role"))
    private List<Role> roles = new ArrayList<>();

    
    public Privilege() {
    }
    
    public Privilege(Integer id, String name) {
        
        this.id_privilege = id;
        this.name = name;
    }
    
    public Integer getId() {
        return this.id_privilege;
    }

    public void setId(Integer id) {
        this.id_privilege = id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}