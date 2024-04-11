package com.blog.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.api.entity.Roles;

public interface RolesRepo extends JpaRepository<Roles, Integer> {

}
