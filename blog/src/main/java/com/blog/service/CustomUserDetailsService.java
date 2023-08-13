package com.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.entity.User;
import com.blog.repository.UsersRepository;

@Service

public class CustomUserDetailsService  implements UserDetailsService{
	
	@Autowired
	 UsersRepository usersRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = usersRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        
        // You need to map your User entity to a UserDetails implementation
        // For example, using Spring Security's User class
        // Assuming your User entity has properties: email, password, roles
        
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
            .username(user.getEmail())
            .password(user.getPassword())
            .roles(user.getRole()) // Replace with your method to get roles
            .build();

        return userDetails;
    }
	}

