package com.kunyong.rds.respository.evaluate;

import com.kunyong.rds.entity.evaluate.EvaluateSubType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/6/8
 */
public interface EvaluateSubTypeRepository extends JpaRepository<EvaluateSubType,Long> {
    List<EvaluateSubType> findBySubType(String subType);

    List<EvaluateSubType> findByPIdAndSubType(int id, String subType);

    int deleteByIdIn(int parseInt);

    EvaluateSubType findById(int id);
}
