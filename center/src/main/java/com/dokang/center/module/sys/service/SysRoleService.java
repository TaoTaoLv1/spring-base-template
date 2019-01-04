package com.dokang.center.module.sys.service;

import com.dokang.center.module.sys.dao.SysRoleRepository;
import com.dokang.center.module.sys.entity.SysRole;
import com.dokang.lib.mybatis.base.service.MybatisBaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author: YwT
 * @create: 2019-01-03 14:56
 **/
@Service
public class SysRoleService extends MybatisBaseServiceImpl<SysRole, String, SysRoleRepository> {
    @Override
    public Object buildQueryExample(Map<String, Object> param) {
        return null;
    }
}
