package com.dokang.center.module.sys.entity;

import com.dokang.lib.base.entity.BaseEntityImpl;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * @author: YwT
 * @create: 2018-12-30 21:30
 **/
@Table(name = "sys_user")
public class SysUser extends BaseEntityImpl implements Serializable {

    @Id
    private String Id;

    private String userName;

    private String password;

    @Transient
    private List<GrantedAuthority> perms;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<GrantedAuthority> getPerms() {
        return perms;
    }

    public void setPerms(List<GrantedAuthority> perms) {
        this.perms = perms;
    }
}
