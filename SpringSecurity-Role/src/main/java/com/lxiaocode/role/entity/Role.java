package com.lxiaocode.role.entity;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author lixiaofeng
 * @date 2020/9/10 上午8:48
 * @blog http://www.lxiaocode.com/
 */
@TableName("role")
public class Role {

    private Integer id;
    private String role;
    private String roleName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
