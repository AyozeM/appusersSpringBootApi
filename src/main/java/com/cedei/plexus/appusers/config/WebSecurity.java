package com.cedei.plexus.appusers.config;

import com.cedei.plexus.appusers.db.RoleRepository;
import com.cedei.plexus.appusers.db.UserRepository;
import com.cedei.plexus.appusers.security.AuthenticationFilter;
import com.cedei.plexus.appusers.security.AuthorizationFilter;
import com.cedei.plexus.appusers.services.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * WebSecurity
 */
@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    
    String update,add;

    @Value("${suso.login.path}")
    String loginUrl;

    private UserDetailsServiceImpl userDetailsService;

    public WebSecurity(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    UserRepository repo;

    @Autowired
    RoleRepository roleRepo;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String regexp = "\\[|\\]";
        this.update= roleRepo.filterByPrivilege(3).toString().replaceAll(regexp, "");
        this.add = roleRepo.filterByPrivilege(2).toString().replaceAll(regexp, "");
        http.cors().and().csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, this.loginUrl).permitAll()
            .antMatchers(HttpMethod.POST,"/api/**").hasAnyAuthority(String.format("%s,%s",this.add,this.update))
            .antMatchers(HttpMethod.DELETE,"/api/**").hasAnyAuthority(this.update)
            .antMatchers(HttpMethod.PUT,"/api/**").hasAnyAuthority(this.update)
            .anyRequest().authenticated().and()
            .addFilterBefore(new AuthenticationFilter(this.loginUrl, authenticationManager()),
                    UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(new AuthorizationFilter(this.repo), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}