package com.dokang.lib.base.service;

import com.dokang.lib.base.entity.BaseEntity;
import com.dokang.lib.base.entity.PageRequestMessage;
import com.dokang.lib.base.entity.PageResponseMessage;

import java.util.List;
import java.util.Map;

/**
 * @author: YwT
 * @description: 基础服务接口，包含基本crud和列表操作
 * @create: 2018-12-28 17:32
 **/
public interface BaseService<T extends BaseEntity> {
    /**
     * 根据主键获取数据
     *
     * @param id
     * @return
     */
    T getById(Object id);

    /**
     * 插入数据
     *
     * @param data
     * @return
     */
    T insert(T data);

    /**
     * 更新数据
     *
     * @param data
     * @return
     */
    T update(T data);

    /**
     * 插入或者更新数据（会使用数据中的id到数据库进行查询判断insert和update）
     *
     * @param data
     * @return
     */
    T insertOrUpdate(T data);

    /**
     * 删除记录
     *
     * @param data
     */
    void delete(T data);

    /**
     * 根据主键删除
     *
     * @param id
     */
    void deleteById(Object id);

    /**
     * 恢复记录
     *
     * @param data
     */
    void unDelete(T data);

    /**
     * 根据主键恢复记录
     *
     * @param id
     */
    void unDeleteById(Object id);

    /**
     * 获取主键的值
     *
     * @param data
     * @return
     */
    Object getKey(T data);

    Object setKey(T data, Object key);

    /**
     * 根据主键获取数据回调，该方法返回false则不进行查询直接返回null
     *
     * @param id
     * @return
     */
    boolean preGet(Object id);

    void afterGet(T data);

    boolean preInsert(T data);

    void afterInsert(T data);

    boolean preUpdate(T data);

    void afterUpdate(T data);

    boolean preDelete(Object id);

    void afterDelete(Object id);

    /**
     * 根据参数查询记录
     *
     * @param param
     * @return
     */
    List<T> getList(Map<String, Object> param);

    /**
     * 根据参数分页查询记录
     *
     * @param param
     * @param page
     * @return
     */
    PageResponseMessage<T> getList(Map<String, Object> param, PageRequestMessage page);
}
