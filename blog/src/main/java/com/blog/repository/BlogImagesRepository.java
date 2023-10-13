package com.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blog.entity.Blog;
import com.blog.entity.BlogImages;

@Repository
public interface BlogImagesRepository extends JpaRepository<BlogImages, Integer>{
//	public List<BlogImages> findBlogImagesByBlog(List<Blog> blog);
	 @Query("SELECT bi FROM BlogImages bi WHERE bi.blog IN :blogs")
	    public List<BlogImages> findBlogImagesByBlogs(@Param("blogs") List<Blog> blogs);

}
