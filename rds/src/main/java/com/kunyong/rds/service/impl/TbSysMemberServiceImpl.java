package com.kunyong.rds.service.impl;

import com.kunyong.rds.entity.TbSysMember.TbSysMember;
import com.kunyong.rds.entity.TbSysTitleConfig.TbSysTitleConfig;
import com.kunyong.rds.respository.tbSysMember.TbSysMemberRespository;
import com.kunyong.rds.service.TbSysMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 贺
 * @Description: TODO
 */
@Service
public class TbSysMemberServiceImpl implements TbSysMemberService {
    @Autowired
    private TbSysMemberRespository tbSysMemberRespository;
    @Override
    public Page<TbSysMember> findAll(Pageable pageable) {
        return tbSysMemberRespository.findAll (pageable);
    }

    @Override
    public TbSysMember saveTbSysMember(TbSysMember tbSysMember) {
        TbSysMember res = null;

        if (tbSysMember != null) {
            Integer id = tbSysMember.getId();
            if (id == 0 || "".equals(id)) {
                res = tbSysMemberRespository.save(tbSysMember);
            } else {
                TbSysMember old = tbSysMemberRespository.findOne (id);
                if (old != null) {
                    old.setName (tbSysMember.getName ());
                    old.setUrl (tbSysMember.getUrl ());
                    old.setRemark (tbSysMember.getRemark ());
                    res = tbSysMemberRespository.saveAndFlush (old);
                }
            }
        }
        return res;
    }

    @Override
    public Integer deleteByIds(List<Integer> lString) {

        return tbSysMemberRespository.deleteByIds(lString);
    }

    @Override
    public TbSysMember getById(Integer id) {
        return tbSysMemberRespository.findOne (id);
    }
}
