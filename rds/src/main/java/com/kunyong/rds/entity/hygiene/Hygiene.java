package com.kunyong.rds.entity.hygiene;

import javax.persistence.*;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/6/6
 */
@Entity
@Table(name = "TB_CHECK_Hygiene_Subject")
public class Hygiene {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Transient
    private boolean isParent ;

    private String name;

    private String dessc;

    @Column(name = "p_id")
    private int pId;

    @Column(name = "level")
    private String levell;

    @Column(name = "store_id")
    private int storeId;

    private double score;

    private int pos;

    @Column(name = "min_score")
    private double minScore;

    @Column(name = "is_auto")
    private String isAuto;


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


    public String getDesc() {
        return dessc;
    }

    public void setDesc(String dessc) {
        this.dessc = dessc;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getPos() {
        return pos;
    }


    public String getLevell() {
        return levell;
    }

    public void setLevell(String levell) {
        this.levell = levell;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public double getMinScore() {
        return minScore;
    }

    public void setMinScore(double minScore) {
        this.minScore = minScore;
    }

    public String getIsAuto() {
        return isAuto;
    }

    public void setIsAuto(String isAuto) {
        this.isAuto = isAuto;
    }


    public String getDessc() {
        return dessc;
    }

    public void setDessc(String dessc) {
        this.dessc = dessc;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }

    @Override
    public String toString() {
        return "Hygiene{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + dessc + '\'' +
                ", pId=" + pId +
                ", level='" + levell + '\'' +
                ", storeId=" + storeId +
                ", score=" + score +
                ", pos=" + pos +
                ", minScore=" + minScore +
                ", isAuto='" + isAuto + '\'' +
                '}';
    }
}
