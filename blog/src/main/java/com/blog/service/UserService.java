package com.blog.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.blog.entity.User;

public interface UserService {
    public User createUser(User user, MultipartFile imageFile);

    public List<User> getAllUsers();

    public User getUserById(int userId);

    public User updateUser(User user);

    public void deleteUser(int userId);

    User loginUser(String email, String password);

    User signUpUser(User usr);
}
