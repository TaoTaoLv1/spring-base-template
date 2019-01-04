package com.dokang.center.module.sys.web;

import com.dokang.center.module.sys.entity.SysUser;
import com.dokang.center.module.sys.service.SysUserService;
import com.dokang.lib.base.web.DefaultBaseControllerImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: YwT
 * @create: 2018-12-30 21:44
 **/
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends DefaultBaseControllerImpl<SysUser, SysUserService> {
}
