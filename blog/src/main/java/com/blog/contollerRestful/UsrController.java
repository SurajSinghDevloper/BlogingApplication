package com.blog.contollerRestful;


import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
	    String hashedPassword = passwordEncoder.encode(newUser.getUserPassword()); // Hash the password
	    newUser.setUserPassword(hashedPassword); // Set the hashed password
	    userRepository.save(newUser);
	    return ResponseEntity.ok("User registered successfully");
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User loginUser) {
	    try {
	        Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(
	                loginUser.getEmail(),
	                loginUser.getUserPassword()
	            )
	        );
	        
	        User user = userRepository.findByEmail(loginUser.getEmail());
	        UserDetails userDetails = userDetailsService.loadUserByUsername(loginUser.getEmail());
	        String token = jwtUtil.generateToken(userDetails);
	        
	        // Create a response object with token and user details
	        LoginResponse loginResponse = new LoginResponse(token, user);
	        
	        return ResponseEntity.ok(loginResponse);
	    } catch (AuthenticationException e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	    }
	}

    
	@PutMapping("/updateUser")
	public ResponseEntity<String> updateUser(
	        @RequestParam("name") String name,
	        @RequestParam("email") String email,
	        @RequestParam("username") String username,
	        @RequestParam("address") String address,
	        @RequestParam("mobile") long mobile,
	        @RequestParam("password") String password,
	        @RequestParam("securityQuestion") String securityQuestion,
	        @RequestParam("securityAnswer") String securityAnswer,
	        @RequestParam("imageFile") MultipartFile imageFile,
	        @AuthenticationPrincipal UserDetails userDetails
	) throws IOException {
	    
	    String authenticatedUsername = userDetails.getUsername();
	    System.out.println("authenticatedUsername ==========  " + authenticatedUsername);
	    
	    if (!authenticatedUsername.equals(username)) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
	    }

	    User existingUser = userService.getUserByEmail(authenticatedUsername);

	    if (existingUser == null) {
	        return ResponseEntity.notFound().build();
	    }

	    if (name.isEmpty() || username.isEmpty()) {
	        return ResponseEntity.badRequest().body("Name and username are required.");
	    }

	    // Only update password if it's provided, to avoid unintentionally changing it
	    if (!password.isEmpty()) {
	        existingUser.setUserPassword(passwordEncoder.encode(password));
	    }

        existingUser.setName(name);
        existingUser.setEmail(email);
        existingUser.setUserName(username);
        existingUser.setUserPassword(passwordEncoder.encode(password)); // You might want to hash the new password here


        User updatedUser = userService.createUser(existingUser, imageFile);
        if (updatedUser != null) {
            return ResponseEntity.ok("Profile updated successfully");
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }
    





    
    
}
