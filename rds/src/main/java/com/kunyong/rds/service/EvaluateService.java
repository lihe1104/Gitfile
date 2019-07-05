package com.kunyong.rds.service;

import com.kunyong.rds.entity.evaluate.Evaluate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/6/8
 */
public interface EvaluateService {

    Page evaluateList(Pageable pageable);

    List save(Evaluate evaluate);

    List removeValid(String ids);

    List delete(String ids);

    List getConfigTree(int id);

    List getI(int subType);
}
