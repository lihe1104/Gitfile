package com.kunyong.lihe.service;

import com.kunyong.lihe.dto.RoleDto;

public interface RoleService {

	void saveRole(RoleDto roleDto);

	void deleteRole(Long id);
}
