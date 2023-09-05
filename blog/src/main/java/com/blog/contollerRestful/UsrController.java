package com.blog.contollerRestful;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.blog.configuration.AuthenticationProcess;
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
	private AuthenticationManager authenticationManager;
	@Autowired
	private CustomUserDetailsService userDetailsService;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UsersRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationProcess authService;

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody User newUser) {
		String hashedPassword = passwordEncoder.encode(newUser.getPassword()); // Hash the password
		newUser.setPassword(hashedPassword); // Set the hashed password
		newUser.setName(newUser.getName());
		newUser.setUsername(newUser.getEmail());
		userRepository.save(newUser);
		return ResponseEntity.ok("User registered successfully");
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User loginUser) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getPassword()));

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

	@PostMapping("/updateUser")
	public ResponseEntity<?> updateUser(
			@RequestParam("name") String name, 
			@RequestParam("email") String email,
			@RequestParam("address") String address, 
			@RequestParam("mobile") long mobile,
			@RequestParam("gender")String gender,
			@RequestParam("bio")String bio,
			@RequestParam("securityQuestion") String securityQuestion,
			@RequestParam("securityAnswer") String securityAnswer,
			@RequestHeader("Authorization") String authorizationHeader) throws IOException {
		ResponseEntity<?> authResponse = authService.authenticateAndRetrieveUser(authorizationHeader);
		if (authResponse.getStatusCode().is2xxSuccessful()) {
			User existingUser = userService.getUserByEmail(email);
			if (existingUser == null) {
				return ResponseEntity.notFound().build();
			}
			existingUser.setName(name);
			existingUser.setEmail(email);
			existingUser.setAddress(address);
			existingUser.setGender(gender);
			existingUser.setBio(bio);
			existingUser.setMobile(mobile);
			existingUser.setSecurityQuestion(securityQuestion);
			existingUser.setSecurityAnswer(securityAnswer);

			User updatedUser = userService.createUser(existingUser);
			if (updatedUser != null) {
				return ResponseEntity.ok("Profile updated successfully");
			} else {
				return ResponseEntity.internalServerError().build();
			}
		} else {
			// Authentication failed, return the same response
			return ResponseEntity.status(authResponse.getStatusCode()).body(authResponse.getBody().toString());
		}
	}

	@Value("${user.image.upload.path}")
	private String imageUploadPath;

	@GetMapping("/images/{imageName:.+}")
	public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
		try {
			// Construct the full path to the image file
			Path imagePath = Paths.get(imageUploadPath).resolve(imageName);
			Resource resource = new UrlResource(imagePath.toUri());

			// Check if the image file exists
			if (resource.exists() && resource.isReadable()) {
				// Set appropriate headers for the image response
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.IMAGE_JPEG); // Adjust MediaType as needed

				// Return the image as a ResponseEntity
				return ResponseEntity.ok().headers(headers).body(resource);
			} else {
				// Return a 404 Not Found response if the image does not exist
				return ResponseEntity.notFound().build();
			}
		} catch (IOException e) {
			// Handle exceptions appropriately (e.g., log the error)
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/updateImage")
	public ResponseEntity<?> updateImage(@RequestParam("email") String email,
			@RequestHeader("Authorization") String authorizationHeader,
			@RequestParam("imageFile") MultipartFile imageFile) throws IOException {
		System.out.println(email);
		// Authenticate and retrieve user using the authService
		ResponseEntity<?> authResponse = authService.authenticateAndRetrieveUser(authorizationHeader);

		if (authResponse.getStatusCode().is2xxSuccessful()) {
			// Authentication successful, continue with image update
			User updatedUser = userService.updateImage(email, imageFile);
			if (updatedUser != null) {
				User user = userService.getUserByEmail(email);
				return ResponseEntity.status(200).body(user);
			} else {
				return ResponseEntity.badRequest().body("Failed to update profile image");
			}
		} else {
			// Authentication failed, return the same response
			return ResponseEntity.status(authResponse.getStatusCode()).body(authResponse.getBody().toString());
		}
	}
}
