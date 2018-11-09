package com.cedei.plexus.appusers.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.cedei.plexus.appusers.models.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Usuario
 */
public class Usuario implements UserDetails {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public Usuario(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static Usuario create(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role ->(GrantedAuthority) new SimpleGrantedAuthority(String.format("ROLE_%s", role.getName()))).collect(Collectors.toList());
        return new Usuario(user.getName(), user.getPassword(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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

}