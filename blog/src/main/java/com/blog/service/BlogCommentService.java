package com.blog.service;

import com.blog.entity.BlogComment;

public interface BlogCommentService {
    public BlogComment createBlogComment(int blogId, int userId, String message);
}
