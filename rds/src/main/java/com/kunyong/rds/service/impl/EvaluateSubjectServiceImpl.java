package com.kunyong.rds.service.impl;

import com.kunyong.rds.entity.evaluate.EvaluateSubType;
import com.kunyong.rds.exceptions.BizException;
import com.kunyong.rds.respository.evaluate.EvaluateSubTypeRepository;
import com.kunyong.rds.service.EvaluateSubJectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/6/9
 */
@Service
public class EvaluateSubjectServiceImpl implements EvaluateSubJectService {

    @Autowired
    private EvaluateSubTypeRepository repository;

    @Override
    public List save(EvaluateSubType sub) {

        EvaluateSubType save = repository.save(sub);
        if (save==null  ){
            throw new BizException("系统异常");
        }
        List list = new ArrayList();
        list.add(save);
        return list;
    }

    @Override
    public List<EvaluateSubType> queryByPID(int id, String subType) {
        List<EvaluateSubType> list = repository.findByPIdAndSubType(id, subType);
        List<EvaluateSubType> transientList = new ArrayList<>();
        for (EvaluateSubType l :
                list) {
            List<EvaluateSubType> byPId = repository.findByPIdAndSubType(l.getId(),subType);
            if (byPId!=null && byPId.size()>0){
                transientList.addAll(byPId);
            }
        }
        list.addAll(transientList);
        return list;
    }

    @Override
    @Transactional
    public List delete(String ids) {
        List<Integer> list = new ArrayList<>();
        for (String id :
                ids.split(",")) {
               int i = repository.deleteByIdIn(Integer.parseInt(id));
               list.add(i);
        }
        return list;
    }
}
