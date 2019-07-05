package com.kunyong.rds.service;

import com.kunyong.rds.entity.evaluate.evaluateConfig.EvaluateConfig;
import com.kunyong.rds.utils.ResponseUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/6/9
 */
public interface EvaluateConfigService {
    Page configList(Pageable pageable);

    List init(List<EvaluateConfig> list);

    List save(EvaluateConfig evaluateConfig);
}
