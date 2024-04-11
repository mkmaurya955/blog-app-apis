package com.blog.api.services;


import java.util.List;

import com.blog.api.payloads.CategoriesDto;



public interface CategoriesServices {
		
	CategoriesDto createCategory(CategoriesDto categoryDto);
	
	CategoriesDto updateCategory(CategoriesDto categoriesDto, int categoryId);
	
	void deleteCategory(int categoryId);
	
	CategoriesDto getCategoryById(int categoryId);
	
	List<CategoriesDto> getAllCategories();
	
}
