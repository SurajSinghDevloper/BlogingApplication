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

    User loginUser(String username, String password);

    public Boolean doesUserExistByEmail(String username);

    User signUpUser(User usr);

    public User getUserByEmail(String username);
}
