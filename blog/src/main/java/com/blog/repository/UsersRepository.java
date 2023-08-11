package com.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entity.User;

public interface UsersRepository extends JpaRepository<User, Integer> {
    public User findByEmail(String email);
}
