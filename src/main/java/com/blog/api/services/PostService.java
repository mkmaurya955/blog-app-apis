package com.blog.api.services;

import com.blog.api.payloads.PaginationResponse;
import com.blog.api.payloads.PostDto;

public interface PostService {
	
	//create 
	PostDto createPost(PostDto postDto,Integer categoryId, Integer userId);
	
	// update 
	PostDto updatePost(PostDto postDto, int postId);
	
	//delete 
	void deletePost(int postId);
	
	//get single post
	PostDto getPostById(int postId);
	
	// get all post 
	PaginationResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
	
	// get all post by category
	PaginationResponse getPostByCategory(int categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
	
	// get all post by user
	PaginationResponse getPostByUser(int userId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
	
	//search post
	PaginationResponse searchPosts(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
	

}
