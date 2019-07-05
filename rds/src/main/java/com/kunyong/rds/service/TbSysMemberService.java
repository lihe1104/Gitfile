package com.kunyong.rds.service;

import com.kunyong.rds.entity.TbSysMember.TbSysMember;
import com.kunyong.rds.entity.TbSysTitleConfig.TbSysTitleConfig;
import com.kunyong.rds.respository.tbSysMember.TbSysMemberRespository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author 贺
 * @Description: TODO
 */
public interface TbSysMemberService {
    Page<TbSysMember> findAll(Pageable pageable);

    TbSysMember saveTbSysMember(TbSysMember tbSysMember);

    Integer deleteByIds(List<Integer> lString);

    TbSysMember getById(Integer id);
}
