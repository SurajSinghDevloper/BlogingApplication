package com.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.entity.Blog;
import com.blog.entity.BlogImages;

@Repository
public interface BlogImagesRepository extends JpaRepository<BlogImages, Integer>{
	public BlogImages findBlogImagesByBlog(Blog blog);

}
