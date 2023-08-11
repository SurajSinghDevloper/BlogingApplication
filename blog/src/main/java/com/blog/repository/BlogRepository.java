package com.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blog.entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
    List<Blog> findByUserId(int userId);

    List<Blog> findByUserIdOrderByDateDesc(int userId);
}