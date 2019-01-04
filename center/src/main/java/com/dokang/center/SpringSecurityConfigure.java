package com.dokang.center;

import com.dokang.center.module.security.authentication.LoginFailHandler;
import com.dokang.center.module.security.authentication.LoginSuccessHandler;
import com.dokang.center.module.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author: YwT
 * @create: 2019-01-03 13:37
 **/
@Configuration
@EnableWebSecurity
public class SpringSecurityConfigure extends WebSecurityConfigurerAdapter {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private LoginFailHandler loginFailHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailHandler)
                .and()
                .authorizeRequests() //请求授权
                .antMatchers("/login.html", "/login").permitAll()
                .anyRequest()        //任何请求
                .authenticated()     //都需要身份认证
                .and()
                .csrf().disable();


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(sysUserService).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * Web层面的配置，一般用来配置无需安全检查的路径
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 前端文件
        web.ignoring().antMatchers("/css/**",
                "/images/**",
                "/js/**");
        // 前端文件vue
        web.ignoring().antMatchers("/static/**");
    }
}
