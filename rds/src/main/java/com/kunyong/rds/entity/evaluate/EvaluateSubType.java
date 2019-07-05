package com.kunyong.rds.entity.evaluate;

import javax.persistence.*;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/6/8
 */
@Entity
@Table(name="tb_evaluate_people_subject")
public class EvaluateSubType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int score;

    private String weight;

    private int seq;

    private String subType;

    private int pId;

    @Column(name = "level")
    private String levell;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getLevell() {
        return levell;
    }

    public void setLevell(String levell) {
        this.levell = levell;
    }

    @Override
    public String toString() {
        return "EvaluateSubject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                ", weight=" + weight +
                ", seq=" + seq +
                ", subType='" + subType + '\'' +
                ", pId=" + pId +
                ", level='" + levell + '\'' +
                '}';
    }
}
