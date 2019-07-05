package com.kunyong.rds.service.impl;

import com.kunyong.rds.entity.evaluate.Evaluate;
import com.kunyong.rds.entity.evaluate.EvaluateSubType;
import com.kunyong.rds.entity.evaluate.evaluateConfig.EvaluateConfig;
import com.kunyong.rds.entity.user.SysUser;
import com.kunyong.rds.exceptions.BizException;
import com.kunyong.rds.respository.evaluate.EvaluateRespository;
import com.kunyong.rds.respository.evaluate.EvaluateSubTypeRepository;
import com.kunyong.rds.respository.evaluate.config.EvaluateConfigRepository;
import com.kunyong.rds.respository.user.UserRepository;
import com.kunyong.rds.service.EvaluateService;
import com.kunyong.rds.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/6/8
 */
@Service
public class EvaluateServiceImpl implements EvaluateService {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");


    @Autowired
    private EvaluateRespository respository;

    @Autowired
    private EvaluateConfigRepository configRepository;

    @Autowired
    private EvaluateRespository  evaluateRespository;


    @Autowired
    private EvaluateSubTypeRepository subTypeRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page evaluateList(Pageable pageable) {
        User user = UserUtil.getLoginUser();
        SysUser sysUser = userRepository.findByTel(user.getUsername());
        int groupId = sysUser.getGroupId();
        Page list = respository.findByGroupId(groupId,pageable);
        return list;
    }

    @Override
    public List save(Evaluate evaluate) {
        List<Evaluate> list = new ArrayList<>();
        User user = UserUtil.getLoginUser();
        SysUser sysUser = userRepository.findByTel(user.getUsername());
        int groupId = sysUser.getGroupId();
        evaluate.setGroupId(groupId);
        Evaluate save = respository.save(evaluate);
        if (save == null) {
            throw new BizException("保存失败");
        }
        list.add(save);
        return list;
    }

    @Override
    public List removeValid(String ids) {
        List<Evaluate> errList = new ArrayList<>();
        for (String i :
                ids.split(",")) {
            int id = Integer.parseInt(i);
            List<EvaluateConfig> configs = configRepository.findBySubType(i);
            if (configs != null && configs.size() > 0) {
                Evaluate e = respository.findById(id);
                errList.add(e);
            }
        }
        return errList;
    }

    @Override
    @Transactional
    public List delete(String ids) {
        List<Integer> integers = new ArrayList<>();
        for (String i :
                ids.split(",")) {
            int si = respository.deleteById(Integer.parseInt(i));
            integers.add(si);
        }
        return integers;
    }

    @Override
    public List getConfigTree(int id) {
        List<EvaluateSubType> list =
                subTypeRepository.findBySubType(String.valueOf(id));
        if (list == null || list.size() <= 0) {
            log.info("查询为空");
            return null;
        }
        return list;
    }

    @Override
    public List getI(int subType) {
        List a  = new ArrayList();
        Evaluate s = evaluateRespository.findById(subType);
        a.add(s);
        return a;
    }
}
