package com.kunyong.rds.respository.evaluate;

import com.kunyong.rds.entity.evaluate.Evaluate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/6/8
 */
public interface EvaluateRespository extends JpaRepository<Evaluate,Long> {
    Page findByGroupId(int groupId, Pageable pageable);

    Evaluate findById(int id);

    int deleteById(int id);
}
