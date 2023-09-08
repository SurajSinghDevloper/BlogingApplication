package com.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.entity.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer>{
	public Blog findBlogByusername(String username);
}
