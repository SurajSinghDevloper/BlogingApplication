package com.blog.repository;

import org.springframework.beans.factory.annotation.Autowired;

import com.blog.entity.User;

public class UserRepositoryImpl implements UserRepositoryCustom {

    private final UsersRepository usersRepository;

    @Autowired
    public UserRepositoryImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public User findUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

}
