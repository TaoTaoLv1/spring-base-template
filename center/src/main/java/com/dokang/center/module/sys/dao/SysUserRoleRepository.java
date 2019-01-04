package com.dokang.center.module.sys.dao;

import com.dokang.center.module.sys.entity.SysRole;
import com.dokang.center.module.sys.entity.SysUser;
import com.dokang.center.module.sys.entity.SysUserRole;
import com.dokang.lib.mybatis.annontation.DBMapper;
import com.dokang.lib.mybatis.base.dao.MybatisBaseRepository;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: YwT
 * @create: 2018-12-30 21:35
 **/
@DBMapper
public interface SysUserRoleRepository extends MybatisBaseRepository<SysUserRole, String> {

    @Select("select r.* from sys_role r, sys_user u, sys_user_role u_r where r.id = u_r.ROLE_ID and u_r.USER_ID = u" +
            ".ID and u_r.IS_DELETE = 0 and u.ID = #{userId}")
    List<SysRole> findSysRolesByUserId(String userId);

    @Select("select * from sys_user where user_name = #{username} and is_delete = 0")
    SysUser findByUserName(String username);

}
