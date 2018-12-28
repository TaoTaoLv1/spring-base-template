package com.dokang.base.web;

import com.dokang.base.entity.BaseEntity;
import com.dokang.base.entity.ResponseMessage;

/**
 * @author: YwT
 * @create: 2018-12-28 17:56
 **/
public interface BaseController<T extends BaseEntity> {
    /***
     * 适合单主键的表查询
     * @param id 主键
     * @return 记录
     */
    ResponseMessage getById(String id);

    /***
     * 用于进行多主键查询
     * @param data 包含所有主键的数据
     * @return 记录
     */
    ResponseMessage get(T data);

    /***
     * 适合单主键的表删除
     * @param id 主键
     * @return 记录
     */
    ResponseMessage deleteById(String id);

    /***
     * 用于进行多主键删除
     * @param data 包含所有主键的数据
     * @return 记录
     */
    ResponseMessage delete(T data);

    /***
     * 用于新增或更新记录，主要根据主键是否为空进行的
     * @param data 包含所有主键的数据
     * @return 记录
     */
    ResponseMessage insertOrUpdate(T data);

    /***
     * 用于新增记录
     * @param data 包含所有主键的数据
     * @return 记录
     */
    ResponseMessage insert(T data);

    /***
     * 用于更新记录
     * @param data 包含所有主键的数据
     * @return 记录
     */
    ResponseMessage update(T data);
}
