package com.kunyong.rds.respository.media;

import com.kunyong.rds.entity.media.MediaConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 贺
 * @Description: TODO
 */
public interface MediaConfigRespository extends JpaRepository<MediaConfig,Integer> {
    @Modifying
    @Transactional
    @Query(value ="DELETE FROM tb_media_config WHERE id IN(?1)",nativeQuery = true)
    Integer deleteByIds(List<Integer> ids);

//    @Query(value ="select * FROM tb_media_config st,tb_sys_org so,tb_media_info m,tb_sys_title_config tc  WHERE st.store_id=so.id" +
//            " and st.info_id=m.id and st.title_id=tc.id and so.orgFullName LIKE CONCAT('%',?1,'%') and tc.level_desc LIKE CONCAT('%',?2,'%') and m.group_id=?3 and m.status=?4 ORDER BY ?#{#pageable}",
//            countQuery = "select count(1) FROM tb_media_config st,tb_sys_org so,tb_media_info m,tb_sys_title_config tc  WHERE sc.store_id=so.id" +
//                    "  and st.info_id=m.id and st.title_id=tc.id and so.orgFullName LIKE CONCAT('%',?1,'%') and tc.level_desc LIKE CONCAT('%',?2,'%') and m.group_id=?3  and m.status=?4" ,nativeQuery = true)
@Query(value = "select mediaconfi0_.id as id_, mediaconfi0_.info_id as info_id_, mediaconfi0_.store_id as store_id_, mediaconfi0_.title_id as title_id_ from tb_media_config mediaconfi0_ inner join tb_sys_org org1_ on mediaconfi0_.store_id=org1_.id inner join tb_sys_title_config tbsystitle2_ on mediaconfi0_.title_id=tbsystitle2_.id inner join tb_media_info media3_ on mediaconfi0_.info_id=media3_.id where org1_.orgFullName like CONCAT('%',?1,'%') and tbsystitle2_.level_desc LIKE CONCAT('%',?2,'%') and media3_.group_id=?3 and media3_.status=?4 ORDER BY ?#{#pageable}",
        countQuery = "select count(1) from tb_media_config mediaconfi0_ inner join tb_sys_org org1_ on mediaconfi0_.store_id=org1_.id inner join tb_sys_title_config tbsystitle2_ on mediaconfi0_.title_id=tbsystitle2_.id inner join tb_media_info media3_ on mediaconfi0_.info_id=media3_.id where org1_.orgFullName like CONCAT('%',?1,'%') and tbsystitle2_.level_desc LIKE CONCAT('%',?2,'%') and media3_.group_id=?3 and media3_.status=?4",nativeQuery = true
)
    Page<MediaConfig> findAll(String orgFullName, String level_desc, int groupId,String status, Pageable pageable);
    @Modifying
    @Transactional
    @Query(value ="DELETE FROM tb_media_config WHERE store_id = ?1 and info_id=?2",nativeQuery = true)
    Integer deleteByStoreId(Integer store_id,Integer info_id);
    @Query(value ="select * FROM tb_media_config WHERE store_id = ?1 and info_id=?2",nativeQuery = true)
    List<MediaConfig> getByStoreId(Integer store_id,Integer info_id);
}
