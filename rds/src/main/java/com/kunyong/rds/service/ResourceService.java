package com.kunyong.rds.service;

import com.kunyong.rds.entity.res.Resource;
import com.kunyong.rds.entity.role.RoleDto;

import java.util.List;

/**
 * @author 贺
 * @Description: TODO
 */
public interface ResourceService {

    List<Resource> listAll();

    List<Resource> listByRoleId(Long roleId);

}
