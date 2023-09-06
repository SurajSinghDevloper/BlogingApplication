package com.blog.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.blog.entity.Blog;
import com.blog.entity.BlogImg;

public interface BlogService {
	 public Blog saveBlogWithImages(Blog blog, List<BlogImg> blogImages);

   
}
