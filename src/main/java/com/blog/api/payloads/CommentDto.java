package com.blog.api.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {
	
	private int id;
	
	@NotEmpty(message="please write on the post")
	private String content;
	
	
}
