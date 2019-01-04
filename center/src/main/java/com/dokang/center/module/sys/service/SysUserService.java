package com.dokang.center.module.sys.service;

import com.dokang.center.module.sys.dao.SysUserRepository;
import com.dokang.center.module.sys.entity.SysRole;
import com.dokang.center.module.sys.entity.SysUser;
import com.dokang.center.module.sys.entity.SysUserDetails;
import com.dokang.lib.mybatis.base.service.MybatisBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: YwT
 * @create: 2018-12-30 21:40
 **/
@Service
public class SysUserService extends MybatisBaseServiceImpl<SysUser, String, SysUserRepository> implements
        UserDetailsService {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> perm = new ArrayList<>();
        SysUser user = this.repository.findByUserName(username);
        if (user != null) {
            List<SysRole> roleList = sysUserRoleService.findSysRolesByUserId(user.getId());
            for (SysRole sysRole : roleList) {
                perm.add(new SimpleGrantedAuthority(sysRole.getCode()));
            }
            user.setPerms(perm);
            SysUserDetails sysUserDetails = new SysUserDetails(user);
            return sysUserDetails;
        }
        return null;
    }

    @Override
    public Object buildQueryExample(Map<String, Object> param) {
        return null;
    }
}
