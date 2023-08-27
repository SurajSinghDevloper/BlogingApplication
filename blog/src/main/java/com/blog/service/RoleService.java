package com.blog.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.blog.entity.Role;
import com.blog.repository.RoleRepository;

public class RoleService {
	 @Autowired
	    private RoleRepository roleDao;

	    public Role createNewRole(Role role) {
	        return roleDao.save(role);
	    }
}
