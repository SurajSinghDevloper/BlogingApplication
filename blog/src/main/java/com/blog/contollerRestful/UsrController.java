package com.blog.contollerRestful;


import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.context.SecurityContextHolder;
import com.blog.configuration.CustomUserDetailsService;
import com.blog.configuration.JwtUtil;
import com.blog.configuration.LoginResponse;
import com.blog.entity.User;
import com.blog.repository.UsersRepository;
import com.blog.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UsrController {
	@Autowired
	private  AuthenticationManager authenticationManager;
	@Autowired
    private  CustomUserDetailsService userDetailsService;
	@Autowired
    private  JwtUtil jwtUtil;
	@Autowired
    private  UsersRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserService userService;


	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody User newUser) {
	    String hashedPassword = passwordEncoder.encode(newUser.getPassword()); // Hash the password
	    newUser.setPassword(hashedPassword); // Set the hashed password
	    userRepository.save(newUser);
	    return ResponseEntity.ok("User registered successfully");
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User loginUser) {
	    try {
	        Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(
	                loginUser.getEmail(),
	                loginUser.getPassword()
	            )
	        );
	        
	        User user = userRepository.findByEmail(loginUser.getEmail());
	        UserDetails userDetails = userDetailsService.loadUserByUsername(loginUser.getEmail());
	        String token = jwtUtil.generateToken(userDetails.getUsername());
	        
	        // Create a response object with token and user details
	        LoginResponse loginResponse = new LoginResponse(token, user);
	        
	        return ResponseEntity.ok(loginResponse);
	    } catch (AuthenticationException e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	    }
	}

    
    @PostMapping("/api/action/updateUser")
    public ResponseEntity<?> updateUser(
            @RequestParam("name") String name,
            @RequestParam("username") String username,
            @RequestParam("address") String address,
            @RequestParam("mobile") long mobile,
            @RequestParam("password") String password,
            @RequestParam("securityQuestion") String securityQuestion,
            @RequestParam("securityAnswer") String securityAnswer,
            @RequestParam("imageFile") MultipartFile imageFile
    ) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }

        String authenticatedUsername = authentication.getName();
        User existingUser = userService.getUserByEmail(authenticatedUsername);

        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }

        if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
            return ResponseEntity.badRequest().body("Required fields are missing.");
        }

        existingUser.setName(name);
        existingUser.setusername(username);
        existingUser.setAddress(address);
        existingUser.setMobile(mobile);
        existingUser.setPassword(passwordEncoder.encode(password)); // You might want to hash the new password here
        existingUser.setSecurityQuestion(securityQuestion);
        existingUser.setSecurityAnswer(securityAnswer);

        User updatedUser = userService.createUser(existingUser, imageFile);
        if (updatedUser != null) {
            return ResponseEntity.ok("Profile updated successfully");
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }
    





    
    
}
