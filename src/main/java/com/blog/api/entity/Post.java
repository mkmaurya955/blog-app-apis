package com.blog.api.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "posts")
@Getter
@Setter
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "post_title", nullable = false)
	private String title;

	@Column(name = "post_content", length = 10000)
	private String content;

	private String imageName;

	private Date addedDate;

	@OneToMany(targetEntity = Comments.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "comment_post_fk", referencedColumnName = "id")
	private Set<Comments> comments = new HashSet<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	private Categories category;

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
