package com.kunyong.rds.respository.tbSysTitleConfig;

import com.kunyong.rds.entity.TbSysTitleConfig.TbSysTitleConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 贺
 * @Description: TODO
 */
public interface TbSysTitleConfigRespository extends JpaRepository<TbSysTitleConfig,Integer> {
    @Modifying
    @Transactional
    @Query(value ="DELETE FROM tb_sys_title_config WHERE id IN(?1)",nativeQuery = true)
    Integer deleteByIds(List<Integer> ids);
    @Query(value ="select * FROM tb_sys_title_config WHERE title_key IN(?1)",nativeQuery = true)
    List<TbSysTitleConfig> getLevelKeys(List<Integer> titleKeys);
    @Query(value ="select * FROM tb_sys_title_config WHERE level_key IN(?1) ORDER BY ?#{#pageable}",
            countQuery = "select count(1) FROM tb_sys_title_config WHERE level_key IN(?1)" ,nativeQuery = true)
    Page<TbSysTitleConfig> getLevelKeys(List<Integer> level_keys, Pageable pageable);
}
