package com.dokang.base.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: YwT
 * @description: 基础实体类
 * @create: 2018-12-28 16:27
 **/
@MappedSuperclass
public class BaseEntityImpl implements BaseEntity, Serializable {

    @JsonIgnore
    protected Date updateTime;

    @JsonIgnore
    protected Date createdTime;

    @JsonIgnore
    protected String isDelete;

    @JsonIgnore
    protected String flag;

    @Override
    public String getFlag() {
        return flag;
    }

    @Override
    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String getIsDelete() {
        return isDelete;
    }

    @Override
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public Date getCreatedTime() {
        return createdTime;
    }

    @Override
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
