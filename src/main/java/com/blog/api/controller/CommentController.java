package com.blog.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.api.payloads.CommentDto;
import com.blog.api.services.CommentServices;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/")
public class CommentController {

	@Autowired
	private CommentServices commentServices;

	@PostMapping("posts/{postId}/comment")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
			@PathVariable("postId") Integer postId) {
		CommentDto comment = this.commentServices.createComment(commentDto, postId);
		return new ResponseEntity<CommentDto>(comment,HttpStatus.OK);
	}
	

}
