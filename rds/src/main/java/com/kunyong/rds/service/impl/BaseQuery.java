/**
 * Copyright (C), 2015-2019, 好运来,一起好运来,好运带来了喜和爱
 * FileName: BaseQuery
 * Author:   zhangsongli
 * Date:     2019/4/20 16:10
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.kunyong.rds.service.impl;

import com.kunyong.rds.annotation.QueryAnnotation;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import sun.awt.SunHints;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 土哥
 * @create 2019/4/20
 */
public class BaseQuery<T> {
    /**
     * 根据对象模型字段给定的 {@link QueryAnnotation} 进行动态查询条件的拼接,支持判空
     *
     * @param t : 对象模型
     * @return : org.springframework.data.jpa.domain.Specification<T>
     * @Author: 土哥
     * @Date: 2019/4/20
     */
    public Specification<T> getUserSpecification(T t) {
        return (root, query, cb) -> {
            Class<?> rootClass = t.getClass();
            Field[] fields = rootClass.getDeclaredFields();
            List<Field> list = Arrays.asList(fields);
            List<Predicate> predicates = new ArrayList<>();
            return getPredicate(root, cb, t, list, predicates);
        };
    }

    private Predicate getPredicate(Root<?> root, CriteriaBuilder cb, T t, List<Field> list, List<Predicate> predicates) {
        for (Field f : list
        ) {
            QueryAnnotation qa = f.getAnnotation(QueryAnnotation.class);
            try {
                if (null != qa) {
                    //获取数据库的列名,如果为默认值，则取字段名，字段名需与数据库的列明保持一致。
                    String column = qa.column();
                    if ("".equals(column)) {
                        column = f.getName();
                    }
                    //对字段取消权限检查，暴力访问。
                    f.setAccessible(true);
                    Object value = f.get(t);
                    if (StringUtils.isEmpty(value)) {
                        continue;
                    }
                    if (value instanceof Integer){
                        if (0 == (int)value){
                            continue;
                        }
                    }
                    switch (qa.func()) {
                        case equal:
                            predicates.add(cb.equal(root.get(column), value));
                            break;
                        case like:
                            predicates.add(cb.like(root.get(column), "%" + value + "%"));
                            break;
                        case ge:
                            predicates.add(cb.ge(root.get(column), (Number) value));
                            break;
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        ;
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
