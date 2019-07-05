package com.kunyong.rds.service.impl;

import com.kunyong.rds.entity.media.MediaConfig;
import com.kunyong.rds.entity.media.MediaInfo;
import com.kunyong.rds.entity.user.SysUser;
import com.kunyong.rds.respository.media.MediaConfigRespository;
import com.kunyong.rds.respository.media.MediaRepository;
import com.kunyong.rds.respository.user.UserRepository;
import com.kunyong.rds.service.MediaConfigService;
import com.kunyong.rds.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 贺
 * @Description: TODO
 */
@Service
public class MediaConfigServiceImpl extends BaseQuery<MediaConfig> implements MediaConfigService {
    @Autowired
    private MediaConfigRespository mediaConfigRespository;
    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public Page<MediaConfig> findAll(MediaConfig mediaConfig, Pageable pageable) {
        MediaInfo media = mediaConfig.getMediaInfo () ==null ? new MediaInfo(): mediaConfig.getMediaInfo ();
        User user = UserUtil.getLoginUser();
        SysUser tel = userRepository.findByTel(user.getUsername());
        int groupId = tel.getGroupId();
        media.setGroupId(groupId);
        media.setStatus ("1");
        mediaConfig.setMediaInfo (media);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("org.orgFullName" , match -> match.contains())
                .withMatcher("tbSysTitleConfig.level_desc" , match -> match.contains());
        Example<MediaConfig> example = Example.of(mediaConfig,matcher);
//        Specification<MediaConfig> specification = this.getUserSpecification (mediaConfig);
//        BooleanBuilder builder = new BooleanBuilder();
//        return mediaConfigRespository.findAll (mediaConfig.getOrg ().getOrgFullName (),mediaConfig.getTbSysTitleConfig ().getLevel_desc (),groupId,"1",pageable);
        Page<MediaConfig> page = mediaConfigRespository.findAll(example, pageable);
        List<MediaConfig> list = page.getContent();
        for (MediaConfig m : list) {
            m.setMediaInfo (m.getMediaInfo ());
            m.setOrg (m.getOrg ());
            m.setTbSysTitleConfig (m.getTbSysTitleConfig ());
//            Integer infoId = m.getInfo_id();
//           MediaInfo mediaInfo= mediaRepository.findById(infoId);
//           m.setMediaInfo(mediaInfo);
        }
        return page;
    
    
    }

    @Override
    public MediaConfig save(MediaConfig mediaConfig) {
        MediaConfig res = null;

        if (mediaConfig != null) {
            Integer id = mediaConfig.getId();
            if (id == null || "".equals(id) || id == 0 ) {
                res = mediaConfigRespository.save(mediaConfig);
            } else {
                MediaConfig old = mediaConfigRespository.findOne (id);
                if (old != null) {
                    old.setInfo_id (mediaConfig.getInfo_id ());
                    old.setStore_id (mediaConfig.getStore_id ());
                    old.setTitle_id (mediaConfig.getTitle_id ());
                    res = mediaConfigRespository.saveAndFlush (old);
                }
            }
        }
        return res;
    }

    @Override
    public Integer deleteByIds(List<Integer> lString) {
        return mediaConfigRespository.deleteByIds(lString);
    }

    @Override
    public MediaConfig getById(Integer id) {
        return mediaConfigRespository.getOne (id);
    }

    @Override
    public Integer deleteByStoreId(Integer store_id,Integer info_id) {
        return mediaConfigRespository.deleteByStoreId (store_id,info_id);
    }

    @Override
    public List<MediaConfig> save(List<MediaConfig> lString) {
        return mediaConfigRespository.save (lString);
    }

    @Override
    public List<MediaConfig> getByStoreId(Integer store_id, Integer info_id) {
        return mediaConfigRespository.getByStoreId(store_id,info_id);
    }
}
