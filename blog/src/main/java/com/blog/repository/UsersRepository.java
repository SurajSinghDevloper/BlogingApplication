package com.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.entity.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {

	public User findByEmail(String email);
    public User findByUsername(String username);
}
