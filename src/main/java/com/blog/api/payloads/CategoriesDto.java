package com.blog.api.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoriesDto {

	private int id;

	@NotEmpty(message = "Category Name is mandatory")
	private String categoryName;

	private String categoryDescription;

}
