package com.blog.api.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.blog.api.entity.Categories;
import com.blog.api.entity.Post;
import com.blog.api.entity.User;
import com.blog.api.exceptions.ResourceNotFoundException;
import com.blog.api.payloads.PaginationResponse;
import com.blog.api.payloads.PostDto;
import com.blog.api.repositories.CategoriesRepo;
import com.blog.api.repositories.PostRepo;
import com.blog.api.repositories.UserRepo;
import com.blog.api.services.PostService;
import com.blog.api.utils.CurrentDateTime;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CategoriesRepo categoriesRepo;

	@Autowired
	private UserRepo userRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer categoryId, Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
		Categories category = this.categoriesRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category Id", categoryId));
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setAddedDate(new Date());
		post.setImageName("default.png");
		post.setUser(user);
		post.setCategory(category);

		post.setCreatedBy(Integer
				.toString(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()));
		post.setCreationTime(new CurrentDateTime().getDateTime());
		Post savedPost = this.postRepo.save(post);
		return this.modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, int postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post ", "post id ", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
//		post.setImageName(postDto.getImageName());
		post.setEditedBy(Integer
				.toString(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()));
		post.setEditionTime(new CurrentDateTime().getDateTime());
		Post updatedPost = this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(int postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post ", "post id ", postId));
		post.setDeletedAt(new CurrentDateTime().getDateTime());
		this.postRepo.save(post);

	}

	@Override
	public PostDto getPostById(int postId) {
		Post postById = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", " post id", postId));
		return this.modelMapper.map(postById, PostDto.class);
	}

	@Override
	public PaginationResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
		Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> pagePosts = this.postRepo.findAll(p);
		List<Post> allPosts = pagePosts.getContent();
		List<PostDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		PaginationResponse paginationResponse = new PaginationResponse(postDtos, pagePosts);
		return paginationResponse;
	}

	@Override
	public PaginationResponse getPostByCategory(int categoryId, Integer pageNumber, Integer pageSize, String sortBy,
			String sortOrder) {
		Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);

		Categories category = this.categoriesRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

		Page<Post> pagesPosts = this.postRepo.findByCategory(category, p);
		List<Post> allPostsByCategory = pagesPosts.getContent();

		List<PostDto> postDtos = allPostsByCategory.stream().map((post -> this.modelMapper.map(post, PostDto.class)))
				.collect(Collectors.toList());
		PaginationResponse paginationResponse = new PaginationResponse(postDtos, pagesPosts);
		return paginationResponse;
	}

	@Override
	public PaginationResponse getPostByUser(int userId, Integer pageNumber, Integer pageSize, String sortBy,
			String sortOrder) {
		Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id ", userId));
		Page<Post> pagesByUser = this.postRepo.findByUser(user, p);
		List<Post> postsByUser = pagesByUser.getContent();
		List<PostDto> postDtos = postsByUser.stream().map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		PaginationResponse paginationResponse = new PaginationResponse(postDtos, pagesByUser);
		return paginationResponse;
	}

	@Override
	public PaginationResponse searchPosts(String keyword, Integer pageNumber, Integer pageSize, String sortBy,
			String sortOrder) {
		Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> pagesPosts = this.postRepo.findByTitleContainingIgnoreCase(keyword, p);
		List<Post> posts = pagesPosts.getContent();
		List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		PaginationResponse paginationResponse = new PaginationResponse(postDtos, pagesPosts);
		return paginationResponse;
	}

}
