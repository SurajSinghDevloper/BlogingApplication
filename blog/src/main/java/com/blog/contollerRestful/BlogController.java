package com.blog.contollerRestful;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.configuration.AuthenticationProcess;
import com.blog.entity.Blog;
import com.blog.entity.BlogImg;
import com.blog.repository.BlogRepository;
import com.blog.service.BlogService;



@RestController
@RequestMapping("/user/blog")
public class BlogController {
    @Autowired
    BlogRepository blogRepository;

	@Autowired
	private AuthenticationProcess authService;
    
    
    @Value("${blog.image.upload.path}")
    private String imageUploadPath;
    @Autowired
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }
    
    @PostMapping("/createBlog")
    public ResponseEntity<?> createBlogWithImages(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("likes") int likes,
            @RequestParam("userId") int userId,
            @RequestParam("email") String email,
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestParam(value = "files", required = true) List<MultipartFile> files) {

        ResponseEntity<?> authResponse = authService.authenticateAndRetrieveUser(authorizationHeader);

        if (!authResponse.getStatusCode().is2xxSuccessful()) {
            // Authentication failed, return the same response
            return ResponseEntity.status(authResponse.getStatusCode()).body(authResponse.getBody().toString());
        }

        try {
            // Create a new Blog entity
            Blog blog = new Blog();
            blog.setTitle(title);
            blog.setContent(content);
            blog.setLikes(likes);
            blog.setDate(LocalDate.now());
            blog.setTime(LocalTime.now());
            blog.setUserId(userId);

            // Create BlogImage entities for each uploaded file
            List<BlogImg > blogImages = new ArrayList<>();
            if (files != null && !files.isEmpty()) {
                for (MultipartFile file : files) {
                	BlogImg blogImage = new BlogImg();
                    blogImage.setImageName(file.getOriginalFilename());
                    blogImage.setBlog(blog); // Associate the image with the blog
                    blogImages.add(blogImage);
                }
            }
            // Save the Blog with the associated BlogImage entities
            blog = blogService.saveBlogWithImages(blog, blogImages);
            return new ResponseEntity<>(blog, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating blog: " + e.getMessage());
        }
    }

}