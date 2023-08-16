package com.blog.contollerRestful;

import com.blog.configuration.JwtUtil;
import com.blog.entity.User;
import com.blog.configuration.AuthenticationRequest;
import com.blog.configuration.AuthenticationResponse;
import com.blog.configuration.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
	
	 @Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private CustomUserDetailsService userDetailsService;

	    @Autowired
	    private JwtUtil jwtUtil;

	    @PostMapping("/api/authenticate")
	    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
	            throws Exception {

	        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
	                authenticationRequest.getEmail(), authenticationRequest.getPassword()));

	        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
	        final String token = jwtUtil.generateToken(userDetails.getUsername());

	        return ResponseEntity.ok(new AuthenticationResponse(token));
	    }
}
