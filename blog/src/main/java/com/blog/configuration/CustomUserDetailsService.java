package com.blog.configuration;


import com.blog.entity.User;
import com.blog.repository.UsersRepository;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	 private final UsersRepository usersRepository;

	    @Autowired
	    public CustomUserDetailsService(UsersRepository usersRepository) {
	        this.usersRepository = usersRepository;
	    }

	    @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	        User user = usersRepository.findByEmail(email);
	        System.out.println("CustomUserDetailsService   USER FOUND ======>>>>>   " + user);
	        
	        if (user == null) {
	            throw new UsernameNotFoundException("User not found with email: " + email);
	        }

	        String role = user.getRole();
	        if (role == null) {
	            role = "USER"; // Set a default role if the user's role is null
	        }
	        System.out.println("CustomUserDetailsService   USER ROLE FOUND ======>>>>>   " + user.getRole());
	        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));
	        return new UserPrincipal(
	            user.getEmail(),
	            user.getPassword(),
	            authorities
	        );
	    }

}
