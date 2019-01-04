package com.dokang.center.module.sys.dao;

import com.dokang.center.module.sys.entity.SysRole;
import com.dokang.lib.mybatis.annontation.DBMapper;
import com.dokang.lib.mybatis.base.dao.MybatisBaseRepository;

/**
 * @author: YwT
 * @create: 2018-12-30 21:35
 **/
@DBMapper
public interface SysRoleRepository extends MybatisBaseRepository<SysRole, String> {

}
