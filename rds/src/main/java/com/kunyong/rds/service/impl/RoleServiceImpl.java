package com.kunyong.rds.service.impl;

import com.kunyong.rds.entity.role.ResRoleLink;
import com.kunyong.rds.entity.role.Role;
import com.kunyong.rds.respository.role.ResRoleLinkRepository;
import com.kunyong.rds.respository.role.RoleRepository;
import com.kunyong.rds.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 贺
 * @Description: TODO
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ResRoleLinkRepository resRoleLinkRepository;

    @Override
    public List<Role> findAllList(String role_name_Zh, int pageNumber, Integer pageSize) {
        return roleRepository.findAllList( role_name_Zh,  pageNumber,  pageSize) ;
    }

    @Override
    public Integer countAllList(String role_name_Zh) {
        return roleRepository.countAllList( role_name_Zh);
    }

    @Override
    public Role saveRole(Role role) {
        Role res = null;

        if (role != null) {
            Integer id = role.getId();
            if (id == null || "".equals(id) || id == 0 ) {
                role.setName (role.getRole_name_Zh ());
                res = roleRepository.save(role);
            } else {
                Role old = roleRepository.findOne (id);
                if (old != null) {
                    old.setRole_name(role.getRole_name ());
                    old.setRole_name_Zh (role.getRole_name_Zh ());
                    old.setName (role.getName ());

                    res = roleRepository.saveAndFlush (old);
                }
            }
        }
        return res;
    }

    @Override
    public Role getById(Integer id) {
        return roleRepository.findOne (id);
    }

    @Override
    public Integer deleteRole(List<Integer> ids) {
      return   roleRepository.deleteById(ids);
    }

    @Override
    public List<Role> findAllList() {
        return roleRepository.findAll ();
    }

    @Override
    public Page findRoleList(Pageable pageable) {
        Page<Role> list = roleRepository.findAll (pageable);
        return list;
    }

    @Override
    public List roleResConfig(int id, String resIds) {
        Integer link = resRoleLinkRepository.deleteRoleLink(id);
        String[] strings = resIds.split(",");
            for (int i = 0; i < strings.length; i++) {
                ResRoleLink resRoleLink = new ResRoleLink();
                resRoleLink.setRoleId(id);
                resRoleLink.setResId(Integer.parseInt(strings[i]));
                resRoleLinkRepository.save(resRoleLink);
            }
        return new ArrayList();
    }
}
