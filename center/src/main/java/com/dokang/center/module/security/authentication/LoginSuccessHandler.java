package com.dokang.center.module.security.authentication;

import com.dokang.center.module.sys.entity.SysUser;
import com.dokang.center.module.sys.entity.SysUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: YwT
 * @description：登录成功处理器
 * @create: 2019-01-04 14:05
 **/
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    //json 转换工具类
    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication
            authentication) throws IOException, ServletException {
        logger.info("登录成功");

        //将 authention 信息打包成json格式返回
        response.setContentType("application/json;charset=UTF-8");
        SysUserDetails sysUserDetails = (SysUserDetails) authentication.getPrincipal();
        SysUser user = sysUserDetails.getUser();
        user.setPassword(null);
        response.getWriter().write(objectMapper.writeValueAsString(user));

    }
}
