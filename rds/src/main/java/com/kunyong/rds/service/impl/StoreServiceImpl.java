package com.kunyong.rds.service.impl;

import com.kunyong.rds.entity.media.MediaConfig;
import com.kunyong.rds.entity.org.Org;
import com.kunyong.rds.entity.user.SysUser;
import com.kunyong.rds.exceptions.BizException;
import com.kunyong.rds.respository.OrgRespository;
import com.kunyong.rds.respository.user.UserRepository;
import com.kunyong.rds.service.StoreService;
import com.kunyong.rds.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/6/6
 */
@Service
public class StoreServiceImpl implements StoreService {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    private OrgRespository orgRespository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page storeList(Pageable pageable)throws BizException {
        User user = UserUtil.getLoginUser();
        SysUser sysUser = userRepository.findByTel(user.getUsername());
        int groupId = sysUser.getGroupId();
            return  orgRespository.findByGroupIdAndLevel(groupId,"3",pageable);
    }

    @Override
    public List<Org> findAllList() {
        Org org = new Org ();
//        User user = UserUtil.getLoginUser();
//        SysUser tel = userRepository.findByTel(user.getUsername());
//        org.setGroupId (tel.getGroupId());
        org.setLevel ("1");
        Example<Org> example = Example.of(org);
        return orgRespository.findAll (example);
//        return orgRespository.findAll ();
    }

    @Override
    public List all() {
        String[] split = "0,1".split(",");
        List<Org> list = orgRespository.findByLevelIn(split);
        for (Org o:
             list) {
            o.setName(o.getOrgFullName());
            List<SysUser> users = userRepository.findByGroupId(o.getId());
            if (!CollectionUtils.isEmpty(users)){
                o.setIsParent("true");
            }
        }
        return list;
    }
}
