package com.cedei.plexus.appusers.security;

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
    /*
     * @Value("${security.token.singkey}") private static String key;
     * 
     * @Value("${security.token.prefix}") private static String prefix;
     * 
     * @Value("${security.token.header}") private static String header;
     */

    private static final String key = "Sus0_3n_Sus0l@d1@";
    private static final String prefix = "Bearer";
    private static final String header = "Authorization";

    static void addAuthentication(HttpServletResponse response, String username) {
        String token = Jwts.builder().setSubject(username).signWith(SignatureAlgorithm.HS512, key).compact();
        response.addHeader(header, String.format("%s %s", prefix, token));
    }

    static Authentication getAuthentication(HttpServletRequest request, String[] roles) {
        String token = request.getHeader(header);
        if (token != null) {
            String user = Jwts.parser().setSigningKey(key).parseClaimsJws(token.replace(prefix, "")).getBody()
                    .getSubject();

            return user != null
                    ? new UsernamePasswordAuthenticationToken(user, null, AuthorityUtils.createAuthorityList(roles))
                    : null;

        }
        return null;
    }

    static String getUser(HttpServletRequest request) {
        String user = null;
        String token = request.getHeader(header);
        if (token != null) {
            user = Jwts.parser().setSigningKey(key).parseClaimsJws(token.replace(prefix, "")).getBody().getSubject();
        }
        return user;
    }
}