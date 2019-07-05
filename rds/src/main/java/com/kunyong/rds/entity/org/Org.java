package com.kunyong.rds.entity.org;

import com.kunyong.rds.annotation.QueryAnnotation;
import com.kunyong.rds.entity.user.SysUser;
import com.kunyong.rds.enums.MatchType;

import javax.persistence.*;
import java.util.List;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/6/5
 */
@Entity
@Table(name = "tb_sys_org")
public class Org {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String orgName;
    @QueryAnnotation(func = MatchType.like)
    private String orgFullName;

    @Column(name = "p_id")
    private Integer pId;

    private String slogan;

    private String icon;

    private String addr;

    @Column(name = "group_id")
    private Integer groupId;

    @Transient
    private String name;

    private String descc;

    private String level;

    private String status;
    @Transient
    private List<Org> child;
    @Transient
    private String isParent;

    @Transient
    private List<SysUser> users;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgFullName() {
        return orgFullName;
    }

    public void setOrgFullName(String orgFullName) {
        this.orgFullName = orgFullName;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getDescc() {
        return descc;
    }

    public void setDescc(String descc) {
        this.descc = descc;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Org> getChild() {
        return child;
    }

    public void setChild(List<Org> child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "Org{" +
                "id=" + id +
                ", orgName='" + orgName + '\'' +
                ", orgFullName='" + orgFullName + '\'' +
                ", pId=" + pId +
                ", slogan='" + slogan + '\'' +
                ", icon='" + icon + '\'' +
                ", addr='" + addr + '\'' +
                ", groupId=" + groupId +
                ", descc='" + descc + '\'' +
                ", level='" + level + '\'' +
                ", status='" + status + '\'' +
                '}';
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsParent() {
        return isParent;
    }

    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }

    public List<SysUser> getUsers() {
        return users;
    }

    public void setUsers(List<SysUser> users) {
        this.users = users;
    }

    public void setUser(List<SysUser> users) {

    }
}
