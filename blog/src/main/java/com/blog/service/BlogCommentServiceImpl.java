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

    public BlogComment createBlogComment(int blogId, int userId, String message) {
        Blog blog = blogService.getBlogById(blogId);
        User user = usersService.getUserById(userId);

        if (blog == null || user == null) {
            // Handle the case when blog or user is not found
            return null;
        }

        BlogComment comment = new BlogComment();
        comment.setMessage(message);
        comment.setLike(0);

        // Set the current date and time using the Java 8 Date-Time API
        Instant now = Instant.ofEpochMilli(System.currentTimeMillis());
        comment.setDate(LocalDate.ofInstant(now, ZoneId.systemDefault()));
        comment.setTime(LocalTime.ofInstant(now, ZoneId.systemDefault()));

        return blogCommentRepository.save(comment);
    }
}
