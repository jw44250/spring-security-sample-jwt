package com.example.securitysample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.securitysample.service.CustomService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	private final CustomService customService;
	
	@Autowired
	public SecurityConfig(CustomService customService) {
		
		this.customService = customService;
	}
	
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
	
	@Override
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(customService).passwordEncoder(passwordEncoder());
	  }
	
	@Bean
	  public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	  };
}
