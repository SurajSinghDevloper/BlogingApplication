package com.blog.contollerRestful;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.blog.entity.Blog;
import com.blog.repository.BlogRepository;
import com.blog.service.BlogService;

@RestController
@RequestMapping("/api/blog")
public class BlogController {
    @Autowired
    BlogRepository blogRepository;

    @Value("${blog.image.upload.path}")
    private String imageUploadPath;
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/create") // Change the mapping as per your requirement
    public ResponseEntity<?> createBlog(@ModelAttribute Blog blog,
            @RequestParam(name = "imageFile", required = false) MultipartFile imageFile) throws IOException {
        if (blog.getTitle().isEmpty() || imageFile == null || imageFile.isEmpty() || blog.getUserId() == 0) {
            return ResponseEntity.badRequest().body("Required fields are missing.");
        }

        Blog createdBlog = blogService.createBlog(blog, imageFile);
        return new ResponseEntity<>(createdBlog, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Blog>> getAllBlogs() {
        List<Blog> blogs = blogService.getAllBlogs();
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @GetMapping("/{blogId}")
    public ResponseEntity<Blog> getBlogById(@PathVariable int blogId) {
        Blog blog = blogService.getBlogById(blogId);
        if (blog != null) {
            return new ResponseEntity<>(blog, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{blogId}")
    public ResponseEntity<Blog> updateBlog(@PathVariable int blogId, @RequestBody Blog blog) {
        Blog existingBlog = blogService.getBlogById(blogId);
        if (existingBlog != null) {
            blog.setBlogId(blogId);
            Blog updatedBlog = blogService.updateBlog(blog);
            return new ResponseEntity<>(updatedBlog, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{blogId}")
    public ResponseEntity<Void> deleteBlog(@PathVariable int blogId) {
        Blog existingBlog = blogService.getBlogById(blogId);
        if (existingBlog != null) {
            blogService.deleteBlog(blogId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Blog>> getAllBlogsByUserId(@PathVariable int userId) {
        List<Blog> blogs = blogService.getAllBlogsByUserId(userId);
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/sorted")
    public ResponseEntity<List<Blog>> getBlogsByUserId(@PathVariable int userId) {
        List<Blog> blogs = blogService.getBlogsByUserId(userId);
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }
}