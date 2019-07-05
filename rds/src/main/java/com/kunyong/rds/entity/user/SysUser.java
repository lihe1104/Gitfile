package com.kunyong.rds.entity.user;

import javax.persistence.*;

/**
 * 用户信息
 *
 * @author 贺
 * @create 2019/5/30
 */
@Entity
@Table(name = "tb_sys_user")
public class SysUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String sex;

    private String tel;

    private String passwd;

    private String title;

    @Transient
    private String checked;

    @Transient
    private String lName;

    @Transient
    private int pId = 0;


    public int getpId() {
        return pId;
    }

    @Column(name = "on_station")
    private String onStation;

    @Column(name = "org_id")
    private int orgId;

    private String level;

    @Column(name = "first_letter")
    private String firstLetter;

    @Column(name = "group_id")
    private int groupId;


    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", tel='" + tel + '\'' +
                ", passwd='" + passwd + '\'' +
                ", title='" + title + '\'' +
                ", onStation='" + onStation + '\'' +
                ", orgId=" + orgId +
                ", level='" + level + '\'' +
                ", firstLetter='" + firstLetter + '\'' +
                ", groupId='" + groupId + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getTel() {
        return tel;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOnStation(String onStation) {
        this.onStation = onStation;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }


    public String getOnStation() {
        return onStation;
    }

    public int getOrgId() {
        return orgId;
    }

    public String getLevel() {
        return level;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }


    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }
}
