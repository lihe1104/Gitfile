package com.kunyong.rds.service;

import com.kunyong.rds.entity.role.RoleDto;

import java.util.List;

/**
 * @author 贺
 * @Description: TODO
 */
public interface ResRoleLinkService {

     boolean saveResRoleLink(RoleDto roleDto);

     Integer deleteRoleLinks(List<Integer> lString);
}
