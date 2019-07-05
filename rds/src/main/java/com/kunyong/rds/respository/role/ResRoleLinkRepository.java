package com.kunyong.rds.respository.role;

import com.kunyong.rds.entity.role.ResRoleLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ResRoleLinkRepository extends JpaRepository<ResRoleLink,Integer> {

    List<ResRoleLink> findByRoleIdIn(List<Integer> roleIds);

    @Modifying
    @Transactional
    @Query(value ="DELETE FROM tb_sys_role_res WHERE role_id = ?1",nativeQuery = true)
    Integer deleteRoleLink(Integer id);
    @Modifying
    @Transactional
    @Query(value ="DELETE FROM tb_sys_role_res WHERE role_id in (?1)",nativeQuery = true)
    Integer deleteRoleLinks(List<Integer> lString);
}
