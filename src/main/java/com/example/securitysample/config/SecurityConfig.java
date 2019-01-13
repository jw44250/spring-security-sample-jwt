package com.example.securitysample.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.cors().and().csrf().disable().authorizeRequests()
			.antMatchers(HttpMethod.POST, "/sign_up").permitAll()
			.antMatchers("/v1/floor1/**").hasRole("USER")
			.antMatchers("/v1/floor2/**").hasRole("ADMIN")
			.and()
			.httpBasic();
		
	}
}
