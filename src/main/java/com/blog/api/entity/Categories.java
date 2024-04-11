package com.blog.api.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "master_categories")
@Getter
@Setter
@NoArgsConstructor
public class Categories {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "category_name", nullable = false, length = 50)
	private String categoryName;

	@Column(name = "category_description", length = 500)
	private String categoryDescription;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL,fetch = FetchType.LAZY )
	private List<Post> posts = new ArrayList<>();
	
	
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

	@Column(name = "meta_data", length = 10000)
	private String metaData;

}
