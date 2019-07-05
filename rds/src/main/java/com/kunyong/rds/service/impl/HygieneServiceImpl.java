package com.kunyong.rds.service.impl;

import com.kunyong.rds.entity.hygiene.Hygiene;
import com.kunyong.rds.exceptions.BizException;
import com.kunyong.rds.respository.hygiene.HygieneRespository;
import com.kunyong.rds.service.HygieneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/6/7
 */
@Service
public class HygieneServiceImpl implements HygieneService {

    @Autowired
    private HygieneRespository hygieneRespository;

    @Override
    public List<Hygiene> getHygiene(int id) {
        return hygieneRespository.findByStoreIdOrderByPos(id);
    }

    @Override
    public List<Hygiene> save(Hygiene hygiene) {

      List<Hygiene> saves = new ArrayList<>();
        saves.add(hygieneRespository.save(hygiene));
        if (saves==null || saves.size()==0 ){
            throw new BizException("保存失败");
        }
        return saves;
    }

    @Override
    public List<Hygiene> queryByPID(int id) {
        if (id  ==0){
            return null;
        }
        List<Hygiene> list = hygieneRespository.findByPId(id);
        List<Hygiene> transientList = new ArrayList<>();
        for (Hygiene l :
                list) {
            List<Hygiene> byPId = hygieneRespository.findByPId(l.getId());
            if (byPId!=null && byPId.size()>0){
                l.setParent(true);
                transientList.addAll(byPId);
            }
        }
        list.addAll(transientList);
        return list;
    }

    @Override
    @Transactional
    public List delete(String ids) {
        String[] strings = ids.split(",");
        int i =0;
        for (String s:strings
             ) {
            int delete = hygieneRespository.deleteByIdIn(Integer.parseInt(s));
            i+=delete;
        }
        if (i<1){
            throw new BizException("删除失败");
        }
        List list = new ArrayList();
        list.add(i);
        return list;
    }
}
