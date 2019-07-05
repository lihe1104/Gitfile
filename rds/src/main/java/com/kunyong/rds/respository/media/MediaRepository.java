package com.kunyong.rds.respository.media;

import com.kunyong.rds.entity.media.MediaInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/6/9
 */
public interface MediaRepository extends JpaRepository<MediaInfo,Integer> {
    Page findAll(Specification<MediaInfo> userSpecification, Pageable pageable);
    @Modifying
    @Transactional
    @Query(value ="DELETE FROM tb_media_info WHERE id IN(?1)",nativeQuery = true)
    Integer deleteByIds(List<Integer> ids);

    MediaInfo findById(Integer infoId);

    Page findByGroupId(int groupId,Pageable pageable);
}
