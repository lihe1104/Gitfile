package com.kunyong.rds.entity.role;

import javax.naming.Name;
import javax.persistence.*;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/6/1
 */
@Entity
@Table(name = "tb_sys_role_res")
public class ResRoleLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "role_id")
    private int roleId;

    @Column(name = "res_id")
    private int resId;


    @Override
    public String toString() {
        return "ResRoleLink{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", resId=" + resId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
