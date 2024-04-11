package com.blog.api.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	String resourceName;
	String fieldName;
	long fieldValue;
	String fieldValueStr;
	
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldVal ) {
		super(String.format("%s  not found with %s : %s ", resourceName,fieldName,fieldVal));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldVal;
	}
	public ResourceNotFoundException(String resourceName, String fieldName, String fieldVal ) {
		super(String.format("%s  not found with %s : %s ", resourceName,fieldName,fieldVal));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValueStr = fieldVal;
	}
	
	
	
	
}
