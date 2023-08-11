package com.blog.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.blog.entity.Blog;

public interface BlogService {
    public Blog createBlog(Blog blog, MultipartFile imageFile);

    public List<Blog> getAllBlogs();

    public Blog getBlogById(int blogId);

    public Blog updateBlog(Blog blog);

    public void deleteBlog(int blogId);

    public List<Blog> getAllBlogsByUserId(int userId);

    public List<Blog> getBlogsByUserId(int userId);
}
