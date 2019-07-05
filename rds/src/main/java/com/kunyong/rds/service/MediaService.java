package com.kunyong.rds.service;

import com.kunyong.rds.entity.media.MediaInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/6/9
 */
public interface MediaService {
    Page mediaList(MediaInfo media, Pageable pageable);

    List<MediaInfo> findAllList();

    MediaInfo save(MediaInfo media);

    Integer deleteByIds(List<Integer> lString);

    MediaInfo getById(Integer id);

    List<MediaInfo> findAllList(MediaInfo mediaInfo);
}
