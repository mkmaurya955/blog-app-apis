package com.blog.api.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.blog.api.utils.ApiResponse;
import com.blog.api.payloads.CategoriesDto;
import com.blog.api.services.CategoriesServices;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

	@Autowired
	private CategoriesServices categoriesServices;
	

	// POST - create category
	@PostMapping("/")
	public ResponseEntity<CategoriesDto> createCategory(@Valid @RequestBody CategoriesDto categoriesDto) {
		CategoriesDto category = this.categoriesServices.createCategory(categoriesDto);
		return new ResponseEntity<CategoriesDto>(category, HttpStatus.CREATED);
	}

	// PUT - update category
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoriesDto> udpateCategory(@PathVariable("categoryId") int categoryId,
			@Valid @RequestBody CategoriesDto categoriesDto) {
		CategoriesDto updateCategory = this.categoriesServices.updateCategory(categoriesDto, categoryId);

		return new ResponseEntity<CategoriesDto>(updateCategory, HttpStatus.OK);
	}

	// DELETE - delete category
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") int categoryId) {
		this.categoriesServices.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Successfully", true), HttpStatus.OK);
	}

	// GET - get category by ID
	@GetMapping("{categoryId}")
	public ResponseEntity<CategoriesDto> GetCategoryByID(@PathVariable("categoryId") int categoryId) {
		CategoriesDto categoryById = this.categoriesServices.getCategoryById(categoryId);
		return ResponseEntity.ok(categoryById);
	}

	// GET - get all Category
	@GetMapping("/")
	public ResponseEntity<List<CategoriesDto>> getAllCategory() {
		List<CategoriesDto> allCategories = this.categoriesServices.getAllCategories();
		return  ResponseEntity.ok(allCategories);
	}
	
	
}
