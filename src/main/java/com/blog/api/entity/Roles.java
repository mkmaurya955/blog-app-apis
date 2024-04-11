package com.blog.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "master_roles")
public class Roles {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "role_name", nullable = false, length = 50)
	private String roleName;
	
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
