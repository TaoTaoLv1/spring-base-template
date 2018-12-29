package com.dokang.lib.base.entity;

import java.util.Date;

/**
 * @author: YwT
 * @create: 2018-12-28 16:27
 **/
public interface BaseEntity {

    String DELETE_FIELD_NAME = "isDelete";
    String DELETE_SQLFIELD_NAME = "is_delete";
    String FLAG_TRUE = "1";
    String FLAG_FALSE = "0";

    String ENABLE_FIELD_NAME = "flag";
    String ENABLE_SQLFIELD_NAME = "flag";
    String ENABLE_TRUE = "1";
    String ENABLE_FALSE = "0";

    String getFlag();

    void setFlag(String flag);

    String getIsDelete();

    void setIsDelete(String flag);

    Date getUpdateTime();

    void setUpdateTime(Date updateTime);

    Date getCreatedTime();

    void setCreatedTime(Date createdTime);
}
