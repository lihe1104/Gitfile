package com.kunyong.rds.respository;

import com.kunyong.rds.entity.org.Org;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/6/6
 */
public interface OrgRespository extends JpaRepository<Org,Long> {


    Page findByGroupIdAndLevel(Integer groupId,String level, Pageable pageable);

    List findByLevelIn(String[] i);
}
