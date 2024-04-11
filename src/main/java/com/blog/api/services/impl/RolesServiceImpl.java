package com.blog.api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.blog.api.entity.Roles;
import com.blog.api.entity.User;
import com.blog.api.exceptions.ResourceNotFoundException;
import com.blog.api.payloads.RolesDto;
import com.blog.api.repositories.RolesRepo;
import com.blog.api.services.RolesServices;
import com.blog.api.utils.CurrentDateTime;

@Service
public class RolesServiceImpl implements RolesServices {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RolesRepo rolesRepo;

	@Override
	public RolesDto createRole(RolesDto roleDto) {
		Roles role = this.modelMapper.map(roleDto, Roles.class);
		role.setCreatedBy(Integer
				.toString(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()));
		role.setCreationTime(new CurrentDateTime().getDateTime());
		Roles savedRole = this.rolesRepo.save(role);
		return this.modelMapper.map(savedRole, RolesDto.class);
	}

	@Override
	public RolesDto updateRole(RolesDto rolesDto, int roleId) {
		Roles role = this.rolesRepo.findById(roleId)
				.orElseThrow(() -> new ResourceNotFoundException("Role", "role id:", roleId));
		role.setRoleName(rolesDto.getRoleName());
		role.setEditedBy(Integer
				.toString(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()));
		role.setEditionTime(new CurrentDateTime().getDateTime());
		Roles updatedRole = this.rolesRepo.save(role);
		return this.modelMapper.map(updatedRole, RolesDto.class);
	}

	@Override
	public void deleteRole(int roleId) {
		Roles role = this.rolesRepo.findById(roleId)
				.orElseThrow(() -> new ResourceNotFoundException("Role", "role id:", roleId));
		role.setDeletedAt(new CurrentDateTime().getDateTime());
		this.rolesRepo.save(role);

	}

	@Override
	public RolesDto getRoleById(int roleId) {
		Roles role = this.rolesRepo.findById(roleId)
				.orElseThrow(() -> new ResourceNotFoundException("Role", "role id:", roleId));
		return this.modelMapper.map(role, RolesDto.class);
	}

	@Override
	public List<RolesDto> getAllRoles() {
		List<Roles> allRoles = this.rolesRepo.findAll();
		return allRoles.stream().map((role) -> this.modelMapper.map(role, RolesDto.class)).collect(Collectors.toList());
	}

}
