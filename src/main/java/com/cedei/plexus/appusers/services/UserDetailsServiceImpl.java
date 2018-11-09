package com.cedei.plexus.appusers.services;

import com.cedei.plexus.appusers.db.UserRepository;
import com.cedei.plexus.appusers.security.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * UserDetails
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        com.cedei.plexus.appusers.models.User usuario = repository.findByName(name);
        if (usuario == null) {
            throw new UsernameNotFoundException(name);
        }
        return new Usuario(usuario);
    }
}