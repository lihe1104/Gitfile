package com.kunyong.rds.service;

import com.kunyong.rds.entity.role.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author 贺
 * @Description: TODO
 */
public interface RoleService {

    List<Role> findAllList(String role_name_Zh, int pageNumber, Integer pageSize);

    Integer countAllList(String role_name_Zh);

    Role saveRole(Role role);

    Role getById(Integer id);

    Integer deleteRole(List<Integer> ids);

    List<Role> findAllList();

    Page findRoleList(Pageable pageable);

    List roleResConfig(int id,String resIds);
}
