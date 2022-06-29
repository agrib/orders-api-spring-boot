package com.classpath.orderrestapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@Configuration
public class WebSecurityConfiguration  extends WebSecurityConfigurerAdapter {
	
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/h2-console/**", "/login**", "/logout**")
				.permitAll()
			.antMatchers(HttpMethod.GET, "/api/orders/**")
				.hasAnyRole("Everyone", "super_admins", "admins")
			.anyRequest()
				.authenticated()
			.and()
			.oauth2ResourceServer()
			.jwt();
	}
	
	
	@Bean
	public JwtAuthenticationConverter jwtAuthenticationConverter() {
		JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
		JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
		
		jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("groups");
		jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
		
		jwtConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
		return jwtConverter;
	}
}
