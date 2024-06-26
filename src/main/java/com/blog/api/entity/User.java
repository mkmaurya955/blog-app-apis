package com.blog.api.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "BLOG_USERS")
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "user_name", nullable = false, length = 100)
	private String name;

	@Column(name = "email", nullable = false, length = 100)
	private String email;

	@Column(name = "password", nullable = false, length = 100)
	private String password;

	@Column(name = "about")
	private String about;

	@OneToMany(targetEntity = Post.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "post_user_fk", referencedColumnName = "id")
	private List<Post> posts = new ArrayList<>();

	@OneToMany(targetEntity = Comments.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "comment_user_fk", referencedColumnName = "id")
	private Set<Comments> comments = new HashSet<>();

	@OneToMany(targetEntity = Roles.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "role_user_fk", referencedColumnName = "id")
	private Set<Roles> roles = new HashSet<>();

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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles.stream().map((role) -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
