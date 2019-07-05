package com.kunyong.rds.entity.TbSysMember;

import com.kunyong.rds.annotation.ExcelAttribute;

import javax.persistence.*;

/**
 * tb_sys_member 实体类
 * 2019-06-09 20:02:33 贺
 */
@Entity
@Table(name = "tb_sys_member")
public class TbSysMember {
    //id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //name
    @ExcelAttribute (name = "名称")
    private String name;
    //url
    @ExcelAttribute (name = "企业logo")
    private String url;
    //remark
    @ExcelAttribute (name = "备注")
    private String remark;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    @Override
    public String toString() {
        return "TbSysMember{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
