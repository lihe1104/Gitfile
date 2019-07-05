package com.kunyong.rds.respository.hygiene;

import com.kunyong.rds.entity.hygiene.Hygiene;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/6/7
 */
public interface HygieneRespository extends JpaRepository<Hygiene,Long> {

    List<Hygiene> findByStoreIdOrderByPos(int storeId);

    List<Hygiene> findByPId(int pId);

    int deleteByIdIn(int id);

}
