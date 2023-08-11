package com.blog.contollerRestful;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.entity.User;
import com.blog.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @Value("${blog.image.upload.path}")
    private String imageUploadPath;

    @PostMapping("/createUser")
    public ResponseEntity<?> saveUser(@RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("mobile") long mobile,
            @RequestParam("password") String password,
            @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        // Validation for required fields and image file
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || imageFile.isEmpty()) {
            return ResponseEntity.badRequest().body("Required fields are missing.");
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setMobile(mobile);
        user.setPassword(password);

        return ResponseEntity.ok(userService.createUser(user, imageFile));
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

    @PostMapping("/user/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        // Validation for required fields
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return ResponseEntity.badRequest().body("Required fields are missing.");
        }

        User loggedInUser = userService.loginUser(email, password);

        if (loggedInUser != null) {
            return ResponseEntity.ok(loggedInUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
        }
    }

}
