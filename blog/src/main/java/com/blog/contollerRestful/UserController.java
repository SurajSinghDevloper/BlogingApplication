package com.blog.contollerRestful;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.security.Principal;
import com.blog.configuration.AuthenticationRequest;
import com.blog.configuration.CustomUserDetailsService;
import com.blog.configuration.JwtUtil;
import com.blog.entity.User;

import com.blog.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Value("${blog.image.upload.path}")
	private String imageUploadPath;

	@PostMapping("/updateUser")
	public ResponseEntity<?> updateUser(@RequestParam("name") String name, @RequestParam("username") String username,
			@RequestParam("email") String email, @RequestParam("address") String address,
			@RequestParam("mobile") long mobile, @RequestParam("password") String password,
			@RequestParam("securityQuestion") String securityQuestion,
			@RequestParam("securityAnswer") String securityAnswer, @RequestParam("imageFile") MultipartFile imageFile,
			@AuthenticationPrincipal Principal principal) throws IOException {

		 String authenticatedUsername = principal != null ? principal.getName() : null;
		    System.out.println("Authenticated Username:"+ authenticatedUsername); // Add this line for debugging

		    if (authenticatedUsername == null || !authenticatedUsername.equals(email)) {
		        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
		    }

		User existingUser = userService.getUserByEmail(email);
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
		existingUser.setPassword(password);
		existingUser.setSecurityQuestion(securityQuestion);
		existingUser.setSecurityAnswer(securityAnswer);

		User updatedUser = userService.createUser(existingUser, imageFile);
		if (updatedUser != null) {
			return ResponseEntity.ok("Profile updated successfully");
		} else {
			return ResponseEntity.internalServerError().build();
		}

	}

	@GetMapping("/getUser/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id) {
		User user = userService.getUserById(id);
		if (user != null) {
			// Construct the image URL using the image name
			String imageUrl = "/emp/image/" + user.getProfileImg();
			user.setProfileImg(imageUrl);
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Endpoint to serve the image
	@GetMapping("/image/{imageName:.+}")
	public ResponseEntity<byte[]> serveImage(@PathVariable String imageName) {
		try {
			Path imagePath = Paths.get(imageUploadPath + imageName);
			byte[] imageBytes = Files.readAllBytes(imagePath);
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}

	public String generateAuthenticationToken(AuthenticationRequest authenticationRequest)
			throws AuthenticationException {
		// Step 1: Authenticate user
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getEmail(), authenticationRequest.getPassword()));

		// Step 2: Load user details
		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

		// Step 3: Generate token
		String token = jwtUtil.generateToken(userDetails.getUsername());

		return token;
	}

	/**
	 * @param loginRequest
	 * @return
	 */
	@PostMapping("/user/login")
	public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginRequest) {
		String email = loginRequest.get("email");
		String password = loginRequest.get("password");

		// Validation for required fields
		if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
			return ResponseEntity.badRequest().body(false);
		}

		User loggedInUser = userService.getUserByEmail(email);

		if (loggedInUser != null && loggedInUser.getPassword().equals(password)) {
			// Create AuthenticationRequest object
			AuthenticationRequest authenticationRequest = new AuthenticationRequest();
			authenticationRequest.setEmail(email);
			authenticationRequest.setPassword(password);
			User usr = userService.getUserByEmail(email);
			try {
				// Generate token using the generateAuthenticationToken method
				String token = generateAuthenticationToken(authenticationRequest);

				// Return token along with status
				Map<String, Object> response = new HashMap<>();
				response.put("status", true);
				response.put("token", token);
				response.put("user", usr);

				return ResponseEntity.ok(response);
			} catch (AuthenticationException e) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
		}
	}

	@PostMapping("/signup/user")
	public ResponseEntity<String> signUp(@RequestBody User user) {
		Boolean userFound = userService.doesUserExistByEmail(user.getusername());
		ResponseEntity<String> res; // Use specific ResponseEntity type

		if (userFound) {
			res = ResponseEntity.badRequest().body("User Email already exists");
		} else {
			User usr = user;
			userService.signUpUser(usr);
			res = ResponseEntity.status(HttpStatus.CREATED).body("User Registered Successfully");
		}
		return res;
	}

}
