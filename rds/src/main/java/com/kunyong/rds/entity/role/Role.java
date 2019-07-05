package com.kunyong.rds.entity.role;

import javax.persistence.*;

/**
 * 角色信息
 *
 * @author 贺
 * @create 2019/5/30
 */
@Entity
@Table(name = "tb_sys_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String role_name;
    private String name;
    private String role_name_Zh;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role_name='" + role_name + '\'' +
                ", name='" + name + '\'' +
                ", role_name_Zh='" + role_name_Zh + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole_name_Zh() {
        return role_name_Zh;
    }

    public void setRole_name_Zh(String role_name_Zh) {
        this.role_name_Zh = role_name_Zh;
    }
}
