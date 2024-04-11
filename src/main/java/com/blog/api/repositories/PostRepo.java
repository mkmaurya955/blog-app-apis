package com.blog.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.api.entity.Categories;
import com.blog.api.entity.Post;
import com.blog.api.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	Page<Post> findByCategory(Categories categories, Pageable p);
	Page<Post> findByUser(User user, Pageable p); 
	Page<Post> findByTitleContainingIgnoreCase(String keyword, Pageable p);
	
}
