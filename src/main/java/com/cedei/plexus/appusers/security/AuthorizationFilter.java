package com.cedei.plexus.appusers.security;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.cedei.plexus.appusers.db.UserRepository;
import com.cedei.plexus.appusers.models.User;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

/**
 * AuthorizationFilter
 */
public class AuthorizationFilter extends GenericFilterBean {

    private UserRepository repo;

    public AuthorizationFilter(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String user = JwtUtil.getUser((HttpServletRequest) request);
        User aux = repo.findByName(user);
        String[] roles = aux.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList())
                .toArray(new String[aux.getRoles().size()]);

        Authentication auth = JwtUtil.getAuthentication((HttpServletRequest) request, roles);
        SecurityContextHolder.getContext().setAuthentication(auth);
        chain.doFilter(request, response);
    }

}
