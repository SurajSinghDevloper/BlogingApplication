package com.blog.repository;

import com.blog.entity.User;

public interface UserRepositoryCustom {
    User findUserByEmail(String email);

	User findByUsername(String username);
}
