package com.blog.api.payloads;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PaginationResponse {

	private List<?> content;
	private long pageNumber;
	private long pageSize;
	private long totalElements;
	private long totalPages;
	private boolean lastPage;



	public PaginationResponse(List<?> content, Page<?> page) {
		super();
		this.content = content;
		this.pageNumber = page.getNumber();
		this.pageSize = page.getSize();
		this.totalElements = page.getTotalElements();
		this.totalPages = page.getTotalPages();
		this.lastPage = page.isLast();
	}
	
}
