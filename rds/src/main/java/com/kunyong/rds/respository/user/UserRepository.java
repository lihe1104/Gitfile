package com.kunyong.rds.respository.user;

import com.kunyong.rds.entity.user.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<SysUser,Long> {

    SysUser findByTel(String tel);

    List<SysUser> findByGroupId(Integer id);
}
