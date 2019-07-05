package com.kunyong.lihe.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.kunyong.lihe.model.TbMediaInfo;

@Mapper
public interface TbMediaInfoDao {

    @Select("select * from tb_media_info t where t.id = #{id}")
    TbMediaInfo getById(Long id);

    @Delete("delete from tb_media_info where id = #{id}")
    int delete(Long id);

    int update(TbMediaInfo tbMediaInfo);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into tb_media_info(mType, name, descc, ope_user, ope_time, url, status, group_id) values(#{mType}, #{name}, #{descc}, #{opeUser}, #{opeTime}, #{url}, #{status}, #{groupId})")
    int save(TbMediaInfo tbMediaInfo);
    
    int count(@Param("params") Map<String, Object> params);

    List<TbMediaInfo> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);
}
