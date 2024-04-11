package com.blog.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
public class Comments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "content", nullable = false)
	private String content;
	
	@ManyToOne
	private Post post;
	
	@ManyToOne
	private User user;
	
	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "creation_time")
	private String creationTime;

	@Column(name = "edited_by")
	private String editedBy;

	@Column(name = "edition_time")
	private String editionTime;

	@Column(name = "deleted_at")
	private String deletedAt;

	@Column(name = "meta_data")
	private String metaData;
	
	
}
