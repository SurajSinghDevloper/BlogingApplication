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

}
