package com.kunyong.rds.respository.tbSysMember;

import com.kunyong.rds.entity.TbSysMember.TbSysMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 贺
 * @Description: TODO
 */
public interface TbSysMemberRespository extends JpaRepository<TbSysMember,Integer> {
    @Modifying
    @Transactional
    @Query(value ="DELETE FROM tb_sys_member WHERE id IN(?1)",nativeQuery = true)
    Integer deleteByIds(List<Integer> ids);
}
