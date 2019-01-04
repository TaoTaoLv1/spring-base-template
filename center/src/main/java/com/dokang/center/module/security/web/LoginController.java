package com.dokang.center.module.security.web;

import com.dokang.lib.base.entity.ResponseMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: YwT
 * @create: 2019-01-04 14:24
 **/
@RestController
@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    //重定向 策略
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    //把当前的请求缓存到 session 里去
    private RequestCache requestCache = new HttpSessionRequestCache();

    /**
     * 当需要身份认证时 跳转到这里
     */
    @PostMapping("/login")
    public ResponseMessage requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws
            IOException {
        //拿到请求对象
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            //获取 跳转url
            String targetUrl = savedRequest.getRedirectUrl();
            logger.info("引发跳转的请求是:" + targetUrl);

            //判断 targetUrl 是不是 .html　结尾, 如果是：跳转到登录页(返回view)
            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
                redirectStrategy.sendRedirect(request, response, "/login.html");
            }

        }
        //如果不是，返回一个json 字符串
        return ResponseMessage.newErrorInstance("访问的服务需要身份认证，请引导用户到登录页");
    }
}
