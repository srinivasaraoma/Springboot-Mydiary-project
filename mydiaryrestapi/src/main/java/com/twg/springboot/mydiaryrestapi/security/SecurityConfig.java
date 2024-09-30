package com.twg.springboot.mydiaryrestapi.security;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public UserDetailsManager configureDataSource(DataSource dataSource)
	{
		UserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
		return userDetailsManager;
	}


	@Bean
	public SecurityFilterChain authorizeHttpRequests(HttpSecurity http) throws Exception
	{
		http
		.authorizeHttpRequests( 
				
				(authorize) -> {
					
					authorize
					.requestMatchers(HttpMethod.DELETE, "/entries/**").hasAuthority("ROLE_ADMIN")
					.requestMatchers(HttpMethod.PUT, "/entries/**").hasAuthority("ROLE_MANAGER")
					.anyRequest().authenticated();
					
					
				}
				
			
			)
		.httpBasic()
		.and()
		.csrf().disable()
		;
		
		
		return http.build();
		
	}
	
	
	

}
