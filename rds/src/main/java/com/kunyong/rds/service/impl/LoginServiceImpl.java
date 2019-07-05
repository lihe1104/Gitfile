package com.kunyong.rds.service.impl;

import com.kunyong.rds.entity.res.Resource;
import com.kunyong.rds.entity.role.ResRoleLink;
import com.kunyong.rds.entity.role.UserRoleLink;
import com.kunyong.rds.entity.user.SysUser;
import com.kunyong.rds.respository.res.ResourceRepository;
import com.kunyong.rds.respository.role.ResRoleLinkRepository;
import com.kunyong.rds.respository.role.RoleRepository;
import com.kunyong.rds.respository.user.UserRepository;
import com.kunyong.rds.respository.user.UserRoleLinkRepository;
import com.kunyong.rds.service.LoginService;
import com.kunyong.rds.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/6/2
 */
@Service
public class LoginServiceImpl implements LoginService {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleLinkRepository userRoleLinkRepository;

    @Autowired
    private ResRoleLinkRepository resRoleLinkRepository;


    @Override
    public List<Resource> findAll() {
        User user = UserUtil.getLoginUser();
        String username = user.getUsername();
        SysUser sysUser = userRepository.findByTel(username);
        List<UserRoleLink> userRoles = userRoleLinkRepository.findByUserId(sysUser.getId());
        if (userRoles == null || userRoles.size()<=0){
            log.info("查询用户角色中间表为空");
            return null;
        }
        List<Integer> roleIds = new ArrayList<>();
        for (UserRoleLink u : userRoles
        ) {
            roleIds.add(u.getRoleId());
        }
        List<ResRoleLink> resRoleLinks = resRoleLinkRepository.findByRoleIdIn(roleIds);
        if (resRoleLinks==null || resRoleLinks.size()<=0){
            log.info("查询资源角色中间表为空");
            return null;
        }
        List<Integer> resIds = new ArrayList<>();
        for (ResRoleLink r:resRoleLinks
             ) {
            resIds.add(r.getResId());
        }
        List<Resource> resources = resourceRepository.findByIdIn(resIds);
        return resources;
    }

    @Override
    @Transactional
    public List roleUserConfig(int id, String userIds) {
        userRoleLinkRepository.deleteByRoleId(id);
        String[] ids = userIds.split(",");
        for (String i :
                ids) {
            UserRoleLink u = new UserRoleLink();
            u.setUserId(Integer.parseInt(i));
            u.setRoleId(id);
            userRoleLinkRepository.save(u);
        }
        return null;
    }

    @Override
    public List<SysUser> queryByGroupId(int groupId,int roleId) {
        List<SysUser> list = userRepository.findByGroupId(groupId);
        List<UserRoleLink> urs = userRoleLinkRepository.findByRoleId(roleId);
        for (SysUser s :
                list) {
                for (UserRoleLink userRoleLink:
                        urs ) {
                    if (userRoleLink.getUserId()==s.getId()){
                        s.setChecked("true");
                    }
                }

                if (s.getLevel()!=null){
                    if ("1".equals(s.getLevel())){
                        s.setlName("集团领导");
                    }else if ("2".equals(s.getLevel())){
                        s.setlName("集团管理");
                    }else if ("3".equals(s.getLevel())){
                        s.setlName("品牌管理");
                    }else if ("4".equals(s.getLevel())){
                        s.setlName("门店管理");
                    }else if ("5".equals(s.getLevel())){
                        s.setlName("门店员工");
                    }
                }
        }
        return list;
    }
}
