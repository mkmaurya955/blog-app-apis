package com.blog.api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.blog.api.entity.Categories;
import com.blog.api.entity.User;
import com.blog.api.exceptions.ResourceNotFoundException;
import com.blog.api.payloads.CategoriesDto;
import com.blog.api.repositories.CategoriesRepo;
import com.blog.api.services.CategoriesServices;
import com.blog.api.utils.CurrentDateTime;

@Service
public class CategoriesServiceImpl implements CategoriesServices {

	@Autowired
	private CategoriesRepo categriesRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoriesDto createCategory(CategoriesDto categoryDto) {
		Categories category = this.modelMapper.map(categoryDto, Categories.class);
		category.setCreatedBy(Integer.toString(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()));
		category.setCreationTime(new CurrentDateTime().getDateTime());
		Categories newCategory = this.categriesRepo.save(category);
		return this.modelMapper.map(newCategory, CategoriesDto.class);
	}

	@Override
	public CategoriesDto updateCategory(CategoriesDto categoriesDto, int categoryId) {
		Categories category = this.categriesRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category Id", categoryId));
		category.setCategoryName(categoriesDto.getCategoryName());
		category.setCategoryDescription(categoriesDto.getCategoryDescription());
		category.setEditedBy(Integer.toString(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()));
		category.setEditionTime(new CurrentDateTime().getDateTime());
		Categories updatedCategory = this.categriesRepo.save(category);
		return this.modelMapper.map(updatedCategory, CategoriesDto.class);
	}

	@Override
	public void deleteCategory(int categoryId) {
		Categories category = this.categriesRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category Id", categoryId));
		category.setDeletedAt(new CurrentDateTime().getDateTime());
		this.categriesRepo.save(category);

	}

	@Override
	public CategoriesDto getCategoryById(int categoryId) {
		Categories categoryById = this.categriesRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category Id", categoryId));
		return this.modelMapper.map(categoryById, CategoriesDto.class);
	}

	@Override
	public List<CategoriesDto> getAllCategories() {
		List<Categories> allCategories = this.categriesRepo.findAll();
		return allCategories.stream().map(category -> this.modelMapper.map(category, CategoriesDto.class))
				.collect(Collectors.toList());
	}

}
