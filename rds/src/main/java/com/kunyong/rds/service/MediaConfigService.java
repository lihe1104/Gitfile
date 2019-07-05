package com.kunyong.rds.service;

import com.kunyong.rds.entity.media.MediaConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author 贺
 * @Description: TODO
 */
public interface MediaConfigService {

    Page<MediaConfig> findAll(MediaConfig mediaConfig, Pageable pageable);

    MediaConfig save(MediaConfig mediaConfig);

    Integer deleteByIds(List<Integer> lString);

    MediaConfig getById(Integer id);

    Integer deleteByStoreId(Integer store_id, Integer Info_id);

    List<MediaConfig> save(List<MediaConfig> lString);

    List<MediaConfig> getByStoreId(Integer store_id, Integer Info_id);
}
