package com.cedei.plexus.appusers.security;

import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cedei.plexus.appusers.db.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * JwtUtil
 */
public class JwtUtil {
    private final static String key = "sus0l@nd1@";

    static void addAuthentication(HttpServletResponse response, String username) {
        String token = Jwts.builder().setSubject(username).signWith(SignatureAlgorithm.HS512, key).compact();
        response.addHeader("Authorization", String.format("Bearer %s", token));
    }

    static Authentication getAuthentication(HttpServletRequest request, String[] roles) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            String user = Jwts.parser().setSigningKey(key).parseClaimsJws(token.replace("Bearer", "")).getBody()
                    .getSubject();

            return user != null
                    ? new UsernamePasswordAuthenticationToken(user, null, AuthorityUtils.createAuthorityList(roles))
                    : null;

        }
        return null;
    }

    static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            String user = Jwts.parser().setSigningKey(key).parseClaimsJws(token.replace("Bearer", "")).getBody()
                    .getSubject();
            return user != null ? new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()) : null;
        }
        return null;
    }
}