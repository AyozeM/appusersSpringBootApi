package com.cedei.plexus.appusers.security;

import java.util.Collection;
import java.util.stream.Collectors;

import com.cedei.plexus.appusers.models.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Usuario
 */
public class Usuario extends User implements UserDetails {

    private static final long serialVersionUID = 1L;

    public Usuario(User user) {
        super();
        super.id_user = user.getId();
        super.name = user.getName();
        super.password = user.getPassword();
        super.roles = user.getRoles();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return super.roles.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return super.name;
    }

}