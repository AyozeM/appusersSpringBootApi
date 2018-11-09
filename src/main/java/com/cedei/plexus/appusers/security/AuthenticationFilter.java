package com.cedei.plexus.appusers.security;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cedei.plexus.appusers.models.Role;
import com.cedei.plexus.appusers.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import io.jsonwebtoken.lang.Arrays;

/**
 * AuthenticationFilter
 */
public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    

    public AuthenticationFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        InputStream body = request.getInputStream();
        User user = new ObjectMapper().readValue(body,User.class);
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword(), Collections.emptyList()));
        //return JwtUtil.getAuthentication(request, getRoles(user.getRoles()));
    }




    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication auth) throws IOException, ServletException {
        JwtUtil.addAuthentication(response, auth.getName());
    }

}