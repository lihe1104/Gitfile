package com.kunyong.rds.entity.evaluate.evaluateConfig;

import org.springframework.util.StringUtils;

import javax.persistence.*;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/6/8
 */
@Entity
@Table(name = "tb_evaluate_config")
public class EvaluateConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String evaUserLevel;

    private String beEvaUserLevel;

    private String evaType;

    private String subType;

    private String methodType;

    private int groupId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEvaUserLevel() {
        return evaUserLevel;
    }

    public void setEvaUserLevel(String evaUserLevel) {
        this.evaUserLevel = evaUserLevel;
    }

    public String getBeEvaUserLevel() {
        return beEvaUserLevel;
    }

    public void setBeEvaUserLevel(String beEvaUserLevel) {
        this.beEvaUserLevel = beEvaUserLevel;
    }

    public String getEvaType() {
        if (!StringUtils.isEmpty(this.evaType)){
            if ("A".equals(this.evaType)){
                return "评上级";
            }else if ("B".equals(this.evaType)){
                return "评同级";
            }else if ("C".equals(this.evaType)){
                return "评下级";
            }else {
                return "暂无数据";
            }
        }else {
            return "暂无数据";
        }
    }

    public void setEvaType(String evaType) {
        this.evaType=evaType;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "EvaluateConfig{" +
                "id=" + id +
                ", evaUserLevel='" + evaUserLevel + '\'' +
                ", beEvaUserLevel='" + beEvaUserLevel + '\'' +
                ", evaType='" + evaType + '\'' +
                ", subType='" + subType + '\'' +
                ", methodType='" + methodType + '\'' +
                ", groupId=" + groupId +
                '}';
    }
}
