package com.blog.api.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.blog.api.entity.Comments;
import com.blog.api.entity.Post;
import com.blog.api.entity.User;
import com.blog.api.exceptions.ResourceNotFoundException;
import com.blog.api.payloads.CommentDto;
import com.blog.api.repositories.CommentRepo;
import com.blog.api.repositories.PostRepo;
import com.blog.api.services.CommentServices;
import com.blog.api.utils.CurrentDateTime;

@Service
public class commentServicesImpl implements CommentServices {

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private PostRepo postRepo;


	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, int postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		Comments comment = this.modelMapper.map(commentDto, Comments.class);
		comment.setPost(post);
		comment.setUser((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		comment.setCreatedBy(Integer.toString(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()));
		comment.setCreationTime(new CurrentDateTime().getDateTime());
		Comments savedComment = this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public CommentDto updateComment(CommentDto commentDto, int commentId) {
		Comments comment = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("comment", "comment id ", commentId));
		comment.setContent(commentDto.getContent());
		comment.setEditedBy(Integer.toString(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()));
		comment.setEditionTime(new CurrentDateTime().getDateTime());
		Comments updatedComment = this.commentRepo.save(comment);
		return this.modelMapper.map(updatedComment, CommentDto.class);
	}

	@Override
	public void deleteCommnet(int commentId) {
		Comments comment = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("comment", "comment id ", commentId));
		comment.setDeletedAt(new CurrentDateTime().getDateTime());
		this.commentRepo.save(comment);
	}

}
