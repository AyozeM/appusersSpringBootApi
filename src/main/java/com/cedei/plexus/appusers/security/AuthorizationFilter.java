package com.cedei.plexus.appusers.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.cedei.plexus.appusers.db.UserRepository;
import com.cedei.plexus.appusers.models.Role;
import com.cedei.plexus.appusers.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Jwts;

/**
 * AuthorizationFilter
 */
public class AuthorizationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        //String[] actualRoles = getRoles(findRoles(getUser((HttpServletRequest) request)));
        //Authentication auth = JwtUtil.getAuthentication((HttpServletRequest) request,actualRoles);
        Authentication auth = JwtUtil.getAuthentication((HttpServletRequest) request);
        SecurityContextHolder.getContext().setAuthentication(auth);
        chain.doFilter(request, response);
    }
/*     private String getUser(HttpServletRequest request){
        String user = null;
        String token = request.getHeader("Authorization");
        if(token != null){
            user = Jwts.parser().setSigningKey("sus0l@nd1@").parseClaimsJws(token.replace("Bearer", "")).getBody().getSubject();
        }
        return user;
    }

    private List<Role> findRoles(String user){
        return x.getRoles();
    }

    private String[] getRoles(List<Role> roles){
        ArrayList<String> r = new ArrayList<>();
        roles.forEach((x) -> {
            r.add(x.getName());
        });
        String[] y = r.toArray(new String[roles.size()]);
        return y;
    }*/

}
