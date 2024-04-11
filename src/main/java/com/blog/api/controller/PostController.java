package com.blog.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.api.config.Constants;
import com.blog.api.payloads.PaginationResponse;
import com.blog.api.payloads.PostDto;
import com.blog.api.services.PostService;
import com.blog.api.utils.ApiResponse;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;

	// POST - create post
	@PostMapping("user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable("userId") Integer userId,
			@PathVariable("categoryId") Integer categoryId) {
		PostDto newPost = this.postService.createPost(postDto, categoryId, userId);

		return new ResponseEntity<PostDto>(newPost, HttpStatus.CREATED);
	}

	// GET - fetch all posts
	@GetMapping("posts")
	public ResponseEntity<PaginationResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = Constants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = Constants.DEFAULT_SORTING, required = false) String sortBy,
			@RequestParam(value = "sortOrder", defaultValue = Constants.DEFAULT_SORTING_ORDER, required = false) String sortOrder) {
		
		PaginationResponse allPost = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortOrder);
		return new ResponseEntity<PaginationResponse>(allPost, HttpStatus.OK);
	}

	// GET - fetch post by id
	@GetMapping("posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Integer postId) {
		PostDto postById = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postById, HttpStatus.OK);
	}

	// GET - fetch posts by category
	@GetMapping("category/{categoryId}/posts")
	public ResponseEntity<PaginationResponse> getPostsByCategory(@PathVariable("categoryId") Integer categoryId,
			@RequestParam(value = "pageNumber", defaultValue = Constants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = Constants.DEFAULT_SORTING, required = false) String sortBy,
			@RequestParam(value = "sortOrder", defaultValue = Constants.DEFAULT_SORTING_ORDER, required = false) String sortOrder
			) {
		PaginationResponse postByCategory = this.postService.getPostByCategory(categoryId,pageNumber,pageSize,sortBy,sortOrder);
		return new ResponseEntity<PaginationResponse>(postByCategory, HttpStatus.OK);
	}

	// GET - fetch posts by user id
	@GetMapping("user/{userId}/posts")
	public ResponseEntity<PaginationResponse> getPostsByUser(@PathVariable("userId") Integer userId,
			@RequestParam(value = "pageNumber", defaultValue = Constants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = Constants.DEFAULT_SORTING, required = false) String sortBy,
			@RequestParam(value = "sortOrder", defaultValue = Constants.DEFAULT_SORTING_ORDER, required = false) String sortOrder
			) {
		PaginationResponse postByUser = this.postService.getPostByUser(userId,pageNumber,pageSize,sortBy,sortOrder);
		return new ResponseEntity<PaginationResponse>(postByUser, HttpStatus.OK);
	}

	// update post
	@PutMapping("posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@PathVariable int postId, @RequestBody PostDto postDto) {
		PostDto updatedPost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
	}

	// delete post
	@DeleteMapping("posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable int postId) {
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted successfully", true), HttpStatus.OK);
	}

	// search post by title
	@GetMapping("posts/search/{keyword}")
	public ResponseEntity<PaginationResponse> searchPostByTitle(@PathVariable("keyword") String keyword,
			@RequestParam(value = "pageNumber", defaultValue = Constants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = Constants.DEFAULT_SORTING, required = false) String sortBy,
			@RequestParam(value = "sortOrder", defaultValue = Constants.DEFAULT_SORTING_ORDER, required = false) String sortOrder
			) {
		PaginationResponse searchPosts = this.postService.searchPosts(keyword,pageNumber,pageSize,sortBy,sortOrder);
		return new ResponseEntity<PaginationResponse>(searchPosts, HttpStatus.OK);
	}

}
