package com.dokang.center.module.sys.entity;

import com.dokang.lib.base.entity.BaseEntityImpl;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "sys_user_role")
public class SysUserRole extends BaseEntityImpl implements Serializable {
    @Id
    private String id;

    private String userId;

    private String roleId;

    public SysUserRole(String id, String userId, String roleId) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
    }

    public SysUserRole() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}