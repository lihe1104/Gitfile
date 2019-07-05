package com.kunyong.rds.service;

import com.kunyong.rds.entity.evaluate.EvaluateSubType;

import java.util.List;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/6/9
 */
public interface EvaluateSubJectService {
    List save(EvaluateSubType sub);

    List<EvaluateSubType> queryByPID(int id, String subType);

    List delete(String ids);
}
