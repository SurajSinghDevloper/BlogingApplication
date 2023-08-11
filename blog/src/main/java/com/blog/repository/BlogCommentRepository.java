package com.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entity.BlogComment;

public interface BlogCommentRepository extends JpaRepository<BlogComment, Integer> {
    List<BlogComment> findByBlogId(int blogId);
}
