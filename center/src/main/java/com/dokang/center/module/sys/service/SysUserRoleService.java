package com.dokang.center.module.sys.service;

import com.dokang.center.module.sys.dao.SysUserRepository;
import com.dokang.center.module.sys.dao.SysUserRoleRepository;
import com.dokang.center.module.sys.entity.SysRole;
import com.dokang.center.module.sys.entity.SysUser;
import com.dokang.center.module.sys.entity.SysUserRole;
import com.dokang.lib.mybatis.base.service.MybatisBaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: YwT
 * @create: 2019-01-03 14:25
 **/
@Service
public class SysUserRoleService extends MybatisBaseServiceImpl<SysUserRole, String, SysUserRoleRepository> {

    public List<SysRole> findSysRolesByUserId(String userId) {
        return this.repository.findSysRolesByUserId(userId);
    }

    public SysUser getUserByUsername(String username) {
        return this.repository.findByUserName(username);
    }

    @Override
    public Object buildQueryExample(Map<String, Object> param) {
        return null;
    }
}
