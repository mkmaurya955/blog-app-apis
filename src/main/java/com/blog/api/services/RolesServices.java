package com.blog.api.services;

import java.util.List;


import com.blog.api.payloads.RolesDto;

public interface RolesServices {

	RolesDto createRole(RolesDto roleDto);

	RolesDto updateRole(RolesDto rolesDto, int roleId);

	void deleteRole(int roleId);

	RolesDto getRoleById(int roleId);

	List<RolesDto> getAllRoles();

}
