package com.blog.contollerRestful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blog.entity.BlogComment;
import com.blog.service.BlogCommentService;

@RestController
@RequestMapping("/api/blog-comments")
public class BlogCommentController {
    @Autowired
    BlogCommentService blogCommentService;

    @PostMapping("/create")
    public ResponseEntity<BlogComment> createBlogComment(@RequestBody CommentRequest request) {
        BlogComment comment = blogCommentService.createBlogComment(request.getBlogId(),
                request.getUserId(),
                request.getMessage());
        if (comment != null) {
            return ResponseEntity.ok(comment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Request DTO for creating blog comments
    public static class CommentRequest {
        private int blogId;
        private int userId;
        private String message;

        // Constructors, getters, and setters
        public CommentRequest() {
        }

        public int getBlogId() {
            return blogId;
        }

        public void setBlogId(int blogId) {
            this.blogId = blogId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
