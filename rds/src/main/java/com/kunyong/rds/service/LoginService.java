package com.kunyong.rds.service;

import com.kunyong.rds.entity.res.Resource;
import com.kunyong.rds.entity.user.SysUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoginService {

    public List<Resource> findAll();

    List roleUserConfig(int id, String userIds);

    List<SysUser> queryByGroupId(int groupId,int roleId);
}
