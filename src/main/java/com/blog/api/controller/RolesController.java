package com.blog.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.api.payloads.RolesDto;
import com.blog.api.services.RolesServices;
import com.blog.api.utils.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/roles")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class RolesController {

	@Autowired
	private RolesServices rolesServices;
	

	// POST - create category
	@PostMapping("/")
	public ResponseEntity<RolesDto> createRole(@Valid @RequestBody RolesDto rolesDto) {
		RolesDto role = this.rolesServices.createRole(rolesDto);
		return new ResponseEntity<RolesDto>(role, HttpStatus.CREATED);
	}

	// PUT - update category
	@PutMapping("/{roleId}")
	public ResponseEntity<RolesDto> udpateRole(@PathVariable("roleId") int roleId,
			@Valid @RequestBody RolesDto rolesDto) {
		RolesDto updateRole = this.rolesServices.updateRole(rolesDto, roleId);

		return new ResponseEntity<RolesDto>(updateRole, HttpStatus.OK);
	}

	// DELETE - delete category
	@DeleteMapping("/{roleId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("roleId") int roleId) {
		this.rolesServices.deleteRole(roleId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Role Deleted Successfully", true), HttpStatus.OK);
	}

	// GET - get category by ID
	@GetMapping("{roleId}")
	public ResponseEntity<RolesDto> getRoleById(@PathVariable("roleId") int roleId) {
		RolesDto roleById = this.rolesServices.getRoleById(roleId);
		return ResponseEntity.ok(roleById);
	}

	// GET - get all Category
	@GetMapping("/")
	public ResponseEntity<List<RolesDto>> getAllRole() {
		List<RolesDto> allRoles = this.rolesServices.getAllRoles();
		return  ResponseEntity.ok(allRoles);
	}
	
}
