package com.blog.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.api.entity.Categories;

public interface CategoriesRepo extends JpaRepository<Categories, Integer> {

}
