package com.lxiaocode.role.entity;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author lixiaofeng
 * @date 2020/9/10 上午8:50
 * @blog http://www.lxiaocode.com/
 */
@TableName("user_role")
public class UserRole {
    private String userId;
    private Integer roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
