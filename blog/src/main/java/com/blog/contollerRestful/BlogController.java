package com.blog.contollerRestful;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.blog.configuration.AuthenticationProcess;
import com.blog.entity.Blog;
import com.blog.entity.User;
import com.blog.repository.UsersRepository;
import com.blog.service.BlogService;
import com.blog.service.UserService;

@RestController
@RequestMapping("/user/blogs")
public class BlogController {
	@Autowired
    private BlogService blogService;
	@Autowired
	private AuthenticationProcess authService;
	@Autowired 
	private UsersRepository userRepo;
    
	
	@PostMapping(value="/create", consumes = "multipart/form-data")
    public ResponseEntity<?> createBlogWithImages(
            @RequestParam("title")String title,
            @RequestParam("content")String content,
            @RequestParam("email")String email,
            @RequestParam("imageFiles") List<MultipartFile> imageFiles,
            @RequestHeader("Authorization") String authorizationHeader) {
        try {
            // Authenticate and retrieve the user
            ResponseEntity<?> authResponse = authService.authenticateAndRetrieveUser(authorizationHeader);
            
            if (authResponse.getStatusCode().is2xxSuccessful()) {
                // User is authenticated, proceed to create the blog
            	User user = userRepo.findByEmail(email);
            	Blog blog =new Blog();
            	blog.setBlogTitle(title);
            	blog.setBlogContent(content);
            	blog.setDate(LocalDate.now());
            	blog.setTime(LocalTime.now());
            	blog.setUser_id(user.getUserId());
            	blog.setUserName(email);
                Blog createdBlog = blogService.createBlogWithImages(blog, imageFiles);
                return ResponseEntity.status(200).body(createdBlog);
            } else {
                // Authentication failed, return the same response
                return ResponseEntity.status(authResponse.getStatusCode()).body(authResponse.getBody().toString());
            }
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating blog.");
        }
    }
	

	@GetMapping("/getBlog/{email}")
    public ResponseEntity<?> getAllBlogsOfUserName(@PathVariable String email, @RequestHeader("Authorization") String authorizationHeader) {
		System.out.println("EMAIL =====>  "+email);
        ResponseEntity<?> authResponse = authService.authenticateAndRetrieveUser(authorizationHeader);
        if (authResponse.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> result = blogService.getAllBlogOfUserName(email);
            if(result != null) {
            return ResponseEntity.ok(result);
            }else {
            	return ResponseEntity.status(0300).body("Something Went Wrong");
            }
        } else {
            return ResponseEntity.status(authResponse.getStatusCode()).body(authResponse.getBody().toString());
        }
    }

}
