package com.kunyong.rds.service;

import com.kunyong.rds.entity.TbSysTitleConfig.TbSysTitleConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author 贺
 * @Description: TODO
 */
public interface TbSysTitleConfigService {
    Page<TbSysTitleConfig> findAll(TbSysTitleConfig tbSysTitleConfig, Pageable pageable);

    TbSysTitleConfig saveSysTitleConfig(TbSysTitleConfig sysTitleConfig);

    Integer deleteByIds(List<Integer> ids);

    TbSysTitleConfig getById(Integer id);

    List<TbSysTitleConfig> getByTitleKeys(List<Integer> titleKeys);

    Page<TbSysTitleConfig> getLevelKeys(List<Integer> lString, Pageable pageable);
}
