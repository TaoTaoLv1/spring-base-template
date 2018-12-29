package com.dokang.lib.mybatis.base.dao;

import tk.mybatis.mapper.common.Mapper;

import java.io.Serializable;

/**
 * @author: YwT
 * @create: 2018-12-28 21:22
 **/
public interface MybatisBaseRepository<T, ID extends Serializable> extends Mapper<T> {
}
