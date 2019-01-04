package com.dokang.center.module.sys.web;

import com.dokang.center.module.sys.entity.SysRole;
import com.dokang.center.module.sys.service.SysRoleService;
import com.dokang.lib.base.web.DefaultBaseControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: YwT
 * @create: 2019-01-03 14:54
 **/
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends DefaultBaseControllerImpl<SysRole, SysRoleService> {
}
