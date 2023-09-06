package com.blog.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blog.entity.Blog;
import com.blog.entity.BlogComment;
import com.blog.entity.User;
import com.blog.repository.BlogCommentRepository;

@Service
public class BlogCommentServiceImpl implements BlogCommentService {
    @Autowired
    BlogCommentRepository blogCommentRepository;

    @Autowired
    BlogService blogService;
    UserService usersService;

    public List<BlogComment> getCommentsByBlogId(int blogId) {
        return blogCommentRepository.findByBlogId(blogId);
    }


}
