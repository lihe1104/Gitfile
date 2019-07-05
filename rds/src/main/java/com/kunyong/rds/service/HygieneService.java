package com.kunyong.rds.service;

import com.kunyong.rds.entity.hygiene.Hygiene;

import java.util.List;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/6/7
 */
public interface HygieneService {
    List<Hygiene> getHygiene(int id);

    List<Hygiene> save(Hygiene hygiene);

    List<Hygiene> queryByPID(int id);

    List delete(String ids);
}
