package com.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blog.entity.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer>{
//	public List<Blog> findBlogByusername(String username);
	  @Query("SELECT b FROM Blog b WHERE b.username = :username")
	    public List<Blog> findBlogsByUsername(@Param("username") String username);
}
