package com.kunyong.rds.service.impl;

import com.kunyong.rds.entity.TbSysTitleConfig.TbSysTitleConfig;
import com.kunyong.rds.respository.tbSysTitleConfig.TbSysTitleConfigRespository;
import com.kunyong.rds.service.TbSysTitleConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 贺
 * @Description: TODO
 */
@Service
public class TbSysTitleConfigServiceImpl implements TbSysTitleConfigService {
    @Autowired
    private TbSysTitleConfigRespository tbSysTitleConfigRespository;

    @Override
    public Page<TbSysTitleConfig> findAll(TbSysTitleConfig tbSysTitleConfig, Pageable pageable) {
        Example<TbSysTitleConfig> example = Example.of(tbSysTitleConfig);
        return tbSysTitleConfigRespository.findAll (example,pageable);
    }

    @Override
    public TbSysTitleConfig saveSysTitleConfig(TbSysTitleConfig sysTitleConfig) {
        TbSysTitleConfig res = null;

        if (sysTitleConfig != null) {
            Integer id = sysTitleConfig.getId();
            if (id == null || "".equals(id) || id == 0 ) {
                res = tbSysTitleConfigRespository.save(sysTitleConfig);
                if(res != null && res.getId ()!=null && res.getId ()!=0){
                    res.setTitle_key (res.getId ().toString ());
                    res = tbSysTitleConfigRespository.saveAndFlush (res);
                }
            } else {
                TbSysTitleConfig old = tbSysTitleConfigRespository.findOne (id);
                if (old != null) {
                    old.setLevel_desc (sysTitleConfig.getLevel_desc ());
                    old.setLevel_key (sysTitleConfig.getLevel_key ());
                    old.setTitle_desc (sysTitleConfig.getTitle_desc ());
                    old.setPos (sysTitleConfig.getPos ());
                    old.setTitle_key (sysTitleConfig.getTitle_key ());
                    res = tbSysTitleConfigRespository.saveAndFlush (old);
                }
            }
        }
        return res;
    }

    @Override
    public Integer deleteByIds(List<Integer> ids) {
        return tbSysTitleConfigRespository.deleteByIds(ids);
    }

    @Override
    public TbSysTitleConfig getById(Integer id) {
        return tbSysTitleConfigRespository.findOne (id);
    }

    @Override
    public List<TbSysTitleConfig> getByTitleKeys(List<Integer> titleKeys) {
        return tbSysTitleConfigRespository.getLevelKeys (titleKeys);
    }

    @Override
    public Page<TbSysTitleConfig> getLevelKeys(List<Integer> level_key, Pageable pageable) {
        return tbSysTitleConfigRespository.getLevelKeys (level_key,pageable);
    }
}
