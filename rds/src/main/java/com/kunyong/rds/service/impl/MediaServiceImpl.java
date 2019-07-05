package com.kunyong.rds.service.impl;

import com.kunyong.rds.entity.media.MediaInfo;
import com.kunyong.rds.entity.user.SysUser;
import com.kunyong.rds.respository.media.MediaRepository;
import com.kunyong.rds.respository.user.UserRepository;
import com.kunyong.rds.service.MediaService;
import com.kunyong.rds.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/6/9
 */
@Service
public class MediaServiceImpl  implements MediaService {

    @Autowired
    private MediaRepository repository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Page mediaList(MediaInfo media, Pageable pageable) {
        User user = UserUtil.getLoginUser();
        SysUser tel = userRepository.findByTel(user.getUsername());
        //media.setGroupId( tel.getGroupId());
       // Example<MediaInfo> example = Example.of(media);
        //Page page = repository.findAll(example, pageable);
        return repository.findByGroupId(tel.getGroupId(),pageable);
    }

    @Override
    public List<MediaInfo> findAllList() {
        MediaInfo media = new MediaInfo();
        User user = UserUtil.getLoginUser();
        SysUser tel = userRepository.findByTel(user.getUsername());
        media.setGroupId( tel.getGroupId());
        media.setStatus ("1");
        Example<MediaInfo> example = Example.of(media);
        return repository.findAll (example);
    }

    @Override
    public MediaInfo save(MediaInfo media) {
        MediaInfo res = null;

        if (media != null) {
            User user = UserUtil.getLoginUser();
            SysUser tel = userRepository.findByTel(user.getUsername());
            media.setGroupId( tel.getGroupId());
            media.setOpeUser (tel.getId ());
            media.setOpeTime (new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss").format (new Date ()));
            Integer id = media.getId();
            if (id == null || "".equals(id) || id == 0 ) {
                res = repository.save(media);
            } else {
                MediaInfo old = repository.findOne (id);
                if (old != null) {
                    old.setmType (media.getmType ());
                    old.setName (media.getName ());
                    old.setDescc (media.getDescc ());
                    old.setOpeUser (media.getOpeUser ());
                    old.setOpeTime (media.getOpeTime ());
                    old.setUrl (media.getUrl ());
                    old.setStatus (media.getStatus ());
                    old.setGroupId (media.getGroupId ());
                    res = repository.saveAndFlush (old);
                }
            }
        }
        return res;
    }

    @Override
    public Integer deleteByIds(List<Integer> lString) {
        return repository.deleteByIds(lString);
    }

    @Override
    public MediaInfo getById(Integer id) {
        return repository.getOne (id);
    }

    @Override
    public List<MediaInfo> findAllList(MediaInfo mediaInfo) {
        User user = UserUtil.getLoginUser();
        SysUser tel = userRepository.findByTel(user.getUsername());
        mediaInfo.setGroupId( tel.getGroupId());
        mediaInfo.setStatus ("1");
        Example<MediaInfo> example = Example.of(mediaInfo);
        return repository.findAll (example);
    }
}
