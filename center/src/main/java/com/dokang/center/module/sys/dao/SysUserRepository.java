package com.dokang.center.module.sys.dao;

import com.dokang.center.module.sys.entity.SysUser;
import com.dokang.lib.mybatis.annontation.DBMapper;
import com.dokang.lib.mybatis.base.dao.MybatisBaseRepository;
import org.apache.ibatis.annotations.Select;

/**
 * @author: YwT
 * @create: 2018-12-30 21:35
 **/
@DBMapper
public interface SysUserRepository extends MybatisBaseRepository<SysUser, String> {

    @Select("select * from sys_user where user_name = #{username} and is_delete = 0")
    SysUser findByUserName(String username);
}
