package com.dokang.center.module.exception.handler;

import com.dokang.lib.base.entity.ResponseMessage;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 使用{@link ControllerAdvice} 注册统一的异常捕捉类 ，所有带{@link org.springframework.web.bind.annotation.RestController
 * }注解的controller的异常将会捕捉
 *
 * @author KennyLau
 */
@RestControllerAdvice(annotations = RestController.class)
public class RestHandlerExceptionController {
    private static Logger logger = LoggerFactory.getLogger(RestHandlerExceptionController.class);
    boolean debug = logger.isDebugEnabled();

    /**
     * 全局异常捕获处理
     *
     * @param ex 异常
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseMessage globalHandler(Exception ex) {
        if (debug) {
            ex.printStackTrace();
        }
        Throwable rootCause = ExceptionUtils.getRootCause(ex);
        return ResponseMessage.newErrorInstance(ex.getMessage() + (rootCause != null ? "||rootCause:" + rootCause
                .getMessage() : ""));
    }

    /**
     * 访问拒绝异常捕获处理（没有登录）
     *
     * @param e 签名验证异常
     * @return
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseMessage accessDeniedHandler(AccessDeniedException e) {
        if (debug) {
            e.printStackTrace();
        }
        return ResponseMessage.newErrorInstance("没有权限访问");
    }

    /**
     * 访问拒绝异常捕获处理（没有登录）
     *
     * @param e 签名验证异常
     * @return
     */
    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseMessage badCredentialsHandler(BadCredentialsException e) {
        if (debug) {
            e.printStackTrace();
        }
        return ResponseMessage.newErrorInstance("用户名或者密码错误，请重新输入！");
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseMessage secureSignHandler(HttpMessageNotReadableException e) {
        if (debug) {
            e.printStackTrace();
        }
        return ResponseMessage.newErrorInstance("参数类型错误！");
    }

}
