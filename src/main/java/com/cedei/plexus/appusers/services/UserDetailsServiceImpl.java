package com.cedei.plexus.appusers.services;

import com.cedei.plexus.appusers.db.UserRepository;
import com.cedei.plexus.appusers.models.Role;
import com.cedei.plexus.appusers.security.Usuario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.List;

/**
 * UserDetails
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository repository;

    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        com.cedei.plexus.appusers.models.User usuario = repository.findByName(name);
        if (usuario == null) {
            throw new UsernameNotFoundException(name);
        }
        //return new User(usuario.getName(), usuario.getPassword(), true, true, true, true, new ArrayList<>());
        
        return Usuario.create(usuario);
    }

    public List<GrantedAuthority> buildGranted(List<Role> roles) {
        List<GrantedAuthority> auths = new ArrayList<>();
        for (Role role : roles) {
            auths.add(new SimpleGrantedAuthority(role.getName()));
        }
        return auths;
    }
}