package com.cedei.plexus.appusers.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * CorsConfig
 */
@Configuration
public class CorsConfig {
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	  final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
       CorsConfiguration config = new CorsConfiguration();
      config.setAllowCredentials(true);
      config.addAllowedOrigin("http://localhost:8081");
      config.addAllowedHeader("*");
      config.addAllowedMethod("*");
      source.registerCorsConfiguration("/**", config);
      FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
	  return source;
  }
}