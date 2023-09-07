package com.blog.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.blog.entity.Blog;

public interface BlogService {
	public Blog createBlogWithImages(Blog blog, List<MultipartFile> imageFiles);
}
