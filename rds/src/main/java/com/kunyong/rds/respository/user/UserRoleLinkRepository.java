package com.kunyong.rds.respository.user;

import com.kunyong.rds.entity.role.UserRoleLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleLinkRepository extends JpaRepository<UserRoleLink,Long> {


    List<UserRoleLink> findByUserId(int userId);

    List findByRoleId(int roleId);

    void deleteByRoleId(int id);
}
