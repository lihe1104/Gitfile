package com.kunyong.rds.respository.role;

import com.kunyong.rds.entity.role.Role;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/5/31
 */
public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Query(value ="SELECT * FROM tb_sys_role r WHERE r.role_name_Zh LIKE CONCAT('%',?1,'%')  limit ?2,?3 ",nativeQuery = true)
    List<Role> findAllList(String role_name_Zh, int pageNumber, Integer pageSize);


    @Query(value ="SELECT count(*) FROM tb_sys_role r WHERE r.role_name_Zh LIKE CONCAT('%',?1,'%') ",nativeQuery = true)
    Integer countAllList(String role_name_Zh);

    @Modifying
    @Transactional
    @Query(value ="DELETE FROM tb_sys_role WHERE id IN(?1)",nativeQuery = true)
    Integer deleteById(List<Integer> ids);

}
