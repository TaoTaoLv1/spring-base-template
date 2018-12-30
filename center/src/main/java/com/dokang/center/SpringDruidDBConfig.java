package com.dokang.center;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author: YwT
 * @create: 2018-12-29 18:36
 **/
@Configuration
public class SpringDruidDBConfig implements EnvironmentAware {
    private static final Logger logger = LoggerFactory.getLogger(SpringDruidDBConfig.class);
    private Environment env;


    @Bean     //声明其为Bean实例
    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();

        datasource.setName(env.getProperty("spring.datasource.name"));
        datasource.setUrl(env.getProperty("spring.datasource.url"));
        datasource.setUsername(env.getProperty("spring.datasource.username"));
        datasource.setPassword(env.getProperty("spring.datasource.password"));
        datasource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));

        //configuration
        datasource.setInitialSize(Integer.valueOf(env.getProperty("spring.datasource.initialSize")));
        datasource.setMinIdle(Integer.valueOf(env.getProperty("spring.datasource.minIdle")));
        datasource.setMaxActive(Integer.valueOf(env.getProperty("spring.datasource.maxActive")));
        datasource.setMaxWait(Integer.valueOf(env.getProperty("spring.datasource.maxWait")));
        datasource.setTimeBetweenEvictionRunsMillis(Integer.valueOf(env.getProperty("spring.datasource" +
                ".timeBetweenEvictionRunsMillis")));
        datasource.setMinEvictableIdleTimeMillis(Integer.valueOf(env.getProperty("spring.datasource" +
                ".minEvictableIdleTimeMillis")));
        datasource.setValidationQuery(env.getProperty("spring.datasource.validationQuery"));
        datasource.setTestWhileIdle(Boolean.getBoolean(env.getProperty("spring.datasource.testWhileIdle")));
        datasource.setTestOnBorrow(Boolean.getBoolean(env.getProperty("spring.datasource.testOnBorrow")));
        datasource.setTestOnReturn(Boolean.getBoolean(env.getProperty("spring.datasource.testOnReturn")));
        datasource.setPoolPreparedStatements(Boolean.getBoolean(env.getProperty("spring.datasource" +
                ".poolPreparedStatements")));
        datasource.setMaxPoolPreparedStatementPerConnectionSize(Integer.valueOf(env.getProperty("spring.datasource" +
                ".maxPoolPreparedStatementPerConnectionSize")));
        try {
            datasource.setFilters(env.getProperty("spring.datasource.filters"));
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }
        datasource.setConnectionProperties(env.getProperty("spring.datasource.connectionProperties"));

        return datasource;
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        logger.info("init Druid Servlet Configuration ");
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),
                "/druid/*");
        // IP白名单
        servletRegistrationBean.addInitParameter("allow", "*");
        // IP黑名单(共同存在时，deny优先于allow)
        //servletRegistrationBean.addInitParameter("deny", "192.168.1.100");
        //控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "admin");
        //是否能够重置数据 禁用HTML页面上的“Reset All”功能
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }
}
