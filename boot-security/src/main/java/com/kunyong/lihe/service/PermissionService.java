package com.kunyong.lihe.service;

import com.kunyong.lihe.model.Permission;

public interface PermissionService {

	void save(Permission permission);

	void update(Permission permission);

	void delete(Long id);
}
