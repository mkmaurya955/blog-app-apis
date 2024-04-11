package com.blog.api.payloads;

import java.util.Date;
import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
	
	private int id;
	
	@NotEmpty(message = "title is mandatory")
	private String title;

	@Size(min = 4, message = "content length should be more than 4")
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private CategoriesDto category;

	private UserDto user;
	
	private Set<CommentDto> comments;
	
	
	

}
