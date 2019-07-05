package com.kunyong.rds.respository.res;

import com.kunyong.rds.entity.res.Resource;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ResourceRepository extends JpaSpecificationExecutor<Resource>, JpaRepository<Resource,Integer> {



    List<Resource> findByIdIn(List<Integer> resIds);

    List<Resource> findByUrl(String uri);

    @Query(value ="select r.* from tb_sys_res r inner join tb_sys_role_res re on re.res_id = r.id where re.role_id = ?1",nativeQuery = true)
    List<Resource> listByRoleId(Long roleId);

}
