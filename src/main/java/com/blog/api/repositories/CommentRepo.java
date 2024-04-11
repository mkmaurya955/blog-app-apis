package com.blog.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.api.entity.Comments;

public interface CommentRepo extends JpaRepository<Comments, Integer> {

}
