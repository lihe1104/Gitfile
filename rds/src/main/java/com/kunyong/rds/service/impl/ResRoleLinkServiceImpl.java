package com.kunyong.rds.service.impl;

import com.kunyong.rds.entity.res.Resource;
import com.kunyong.rds.entity.role.ResRoleLink;
import com.kunyong.rds.entity.role.Role;
import com.kunyong.rds.entity.role.RoleDto;
import com.kunyong.rds.respository.role.ResRoleLinkRepository;
import com.kunyong.rds.service.ResRoleLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 贺
 * @Description: TODO
 */
@Service
public class ResRoleLinkServiceImpl implements ResRoleLinkService {
    @Autowired
    ResRoleLinkRepository resRoleLinkRepository;

    @Override
    public boolean saveResRoleLink(RoleDto roleDto) {
        Role role = roleDto;
        List<Integer> permissionIds = roleDto.getPermissionIds();
        permissionIds.remove(0);
        try {
            if (role.getId() != 0 && !CollectionUtils.isEmpty(permissionIds)) {// 修改
                this.deleteRoleLink(role.getId ());
                List<ResRoleLink> list = new ArrayList<> ();
                for (Integer id : permissionIds) {
                    ResRoleLink resRoleLink= new ResRoleLink ();
                    resRoleLink.setRoleId (role.getId());
                    resRoleLink.setResId (id);
                    list.add (resRoleLink);
                }
               resRoleLinkRepository.save (list);
            }
        }catch (Exception e){
            return false;
        }

        return true;
    }

    @Override
    public Integer deleteRoleLinks(List<Integer> lString) {
        return resRoleLinkRepository.deleteRoleLinks(lString);
    }

    public Integer deleteRoleLink(Integer id) {
       return resRoleLinkRepository.deleteRoleLink (id);
    }
}
