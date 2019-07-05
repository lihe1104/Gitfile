package com.kunyong.rds.service.impl;

import com.kunyong.rds.entity.res.Resource;
import com.kunyong.rds.entity.role.Role;
import com.kunyong.rds.entity.role.RoleDto;
import com.kunyong.rds.respository.res.ResourceRepository;
import com.kunyong.rds.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ResourceServiceImpl implements ResourceService {
    private static final Logger log = LoggerFactory.getLogger(ResourceServiceImpl.class );

    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public List<Resource> listAll() {

        return resourceRepository.findAll ();
    }

    @Override
    public List<Resource> listByRoleId(Long roleId) {
        return resourceRepository.listByRoleId(roleId);
    }

}
