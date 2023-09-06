package com.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.blog.entity.Blog;
import com.blog.entity.BlogImg;
import com.blog.repository.BlogRepository;

import java.util.List;


@Service
public class BlogServiceImpl implements BlogService {
	
	@Autowired
    private BlogRepository blogRepository;



    @Value("${user.image.upload.path}")
    private String imageUploadPath;

    @Override
    public Blog saveBlogWithImages(Blog blog, List<BlogImg> blogImages) {
        blog.setBlogImages(blogImages);
        return blogRepository.save(blog);
    }
   

}
