package com.kunyong.rds.respository.role;

import com.kunyong.rds.entity.role.Role;

import java.util.List;

public class RoleDto extends Role {

	private static final long serialVersionUID = 4218495592167610193L;

	private List<Integer> permissionIds;

	public List<Integer> getPermissionIds() {
		return permissionIds;
	}

	public void setPermissionIds(List<Integer> permissionIds) {
		this.permissionIds = permissionIds;
	}
}
