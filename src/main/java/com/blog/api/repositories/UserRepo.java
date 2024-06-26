package com.blog.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.api.entity.User;


public interface UserRepo extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);
}
