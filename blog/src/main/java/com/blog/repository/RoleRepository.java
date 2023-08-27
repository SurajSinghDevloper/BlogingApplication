package com.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blog.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>{

}
