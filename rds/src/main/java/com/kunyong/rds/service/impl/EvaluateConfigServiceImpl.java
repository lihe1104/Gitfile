package com.kunyong.rds.service.impl;

import com.kunyong.rds.entity.evaluate.evaluateConfig.EvaluateConfig;
import com.kunyong.rds.entity.user.SysUser;
import com.kunyong.rds.exceptions.BizException;
import com.kunyong.rds.respository.evaluate.config.EvaluateConfigRepository;
import com.kunyong.rds.respository.user.UserRepository;
import com.kunyong.rds.service.EvaluateConfigService;
import com.kunyong.rds.utils.ResponseUtil;
import com.kunyong.rds.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/6/9
 */
@Service
public class EvaluateConfigServiceImpl implements EvaluateConfigService {

    @Autowired
    private EvaluateConfigRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page configList(Pageable pageable) {
        User user = UserUtil.getLoginUser();
        SysUser sysUser = userRepository.findByTel(user.getUsername());
        int groupId =sysUser.getGroupId();

        return repository.findByGroupId(groupId,pageable);
    }

    @Override
    public List<EvaluateConfig> init(List<EvaluateConfig> list) {
        User user = UserUtil.getLoginUser();
        SysUser sysUser = userRepository.findByTel(user.getUsername());
        int groupId = sysUser.getGroupId();
        for (EvaluateConfig e :
                list) {
            e.setGroupId(groupId);
        }
        List<EvaluateConfig> configs = repository.findByGroupId(groupId);
        if (configs!=null && configs.size()>0){
            throw new BizException("所在集团已经存在配置项，无需重新进行初始化");
        }

        return repository.save(list);
    }

    @Override
    public List save(EvaluateConfig evaluateConfig) {
        EvaluateConfig e =  repository.findById(evaluateConfig.getId());
        e.setSubType(evaluateConfig.getSubType());
        EvaluateConfig save = repository.save(e);
        if (save!=null){
            List list = new ArrayList();
            list.add(save);
            return list;
        }else {
            throw new BizException("系统异常，请联系维护人员");
        }

    }
}
