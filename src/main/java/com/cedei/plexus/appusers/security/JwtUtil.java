package com.cedei.plexus.appusers.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
/**
 * JwtUtil
 */
public class JwtUtil {

    static Constants aux = new Constants();

    static void addAuthentication(HttpServletResponse response, String email,String name) throws IOException {
        String token = String.format("%s %s", aux.getTOKEN_BEARER_PREFIX(), Jwts.builder().setSubject(email).signWith(SignatureAlgorithm.HS512, aux.getSUPER_SECRET_KEY()).compact());
        response.addHeader("Content-Type", "text/html; charset=utf-8");
        response.addHeader(aux.getHEADER_AUTORIZACION_KEY(),token);

        StringBuilder body = new StringBuilder("{");
        body.append(String.format("\"token\": \"%s\",", token));
        body.append(String.format("\"name\": \"%s\"", name));
        body.append("}");
        response.getWriter().write(body.toString());
        response.flushBuffer();
    }

    static Authentication getAuthentication(HttpServletRequest request, String[] roles) {
        String token = request.getHeader(aux.getHEADER_AUTORIZACION_KEY());
        if (token != null) {
            String user = Jwts.parser().setSigningKey(aux.getSUPER_SECRET_KEY())
                    .parseClaimsJws(token.replace(aux.getTOKEN_BEARER_PREFIX(), "")).getBody().getSubject();

            return user != null
                    ? new UsernamePasswordAuthenticationToken(user, null, AuthorityUtils.createAuthorityList(roles))
                    : null;

        }
        return null;
    }

    static String getUser(HttpServletRequest request) {
        String user = null;
        String token = request.getHeader(aux.getHEADER_AUTORIZACION_KEY());
        if (token != null) {
            user = Jwts.parser().setSigningKey(aux.getSUPER_SECRET_KEY())
                    .parseClaimsJws(token.replace(aux.getTOKEN_BEARER_PREFIX(), "")).getBody().getSubject();
        }
        return user;
    }
}