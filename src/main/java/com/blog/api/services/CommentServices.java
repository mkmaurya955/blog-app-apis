package com.blog.api.services;

import com.blog.api.payloads.CommentDto;

public interface CommentServices {

	CommentDto createComment(CommentDto commentDto,int postId);
	
	CommentDto updateComment(CommentDto commentDto, int commentId);
	
	void deleteCommnet(int commentId);
	
	
	
}
