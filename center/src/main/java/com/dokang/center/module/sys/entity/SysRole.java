package com.dokang.center.module.sys.entity;

import com.dokang.lib.base.entity.BaseEntityImpl;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "sys_role")
public class SysRole extends BaseEntityImpl implements Serializable {
    @Id
    private String id;

    private String code;

    private String name;

    public SysRole(String id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public SysRole() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}