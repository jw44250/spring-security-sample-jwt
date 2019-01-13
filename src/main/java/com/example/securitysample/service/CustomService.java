package com.example.securitysample.service;


import java.util.Arrays;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.securitysample.model.User;

@Component
public class CustomService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		  User user = findUserByUsername(username);

		    UserBuilder builder = null;
		    if (user != null) {
		      builder = org.springframework.security.core.userdetails.User.withUsername(username);
		      builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
		      builder.roles(user.getRoles());
		    } else {
		      throw new UsernameNotFoundException("User not found.");
		    }

		    return builder.build();
	}
	
	
	
	
	
	private User findUserByUsername(String username) {
		
		if(username.equalsIgnoreCase("admin")) {
			
			String [] roles = {"USER","ADMIN"};
			return new User(username, "pass",roles);
			
		}
		return null;
	}

}
