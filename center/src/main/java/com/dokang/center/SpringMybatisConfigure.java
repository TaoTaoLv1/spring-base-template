package com.dokang.center;

import com.dokang.lib.base.entity.BaseEntity;
import com.dokang.lib.mybatis.annontation.DBMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author: YwT
 * @create: 2018-12-30 16:45
 **/
@Configuration
public class SpringMybatisConfigure implements EnvironmentAware {

    private Environment env;

    @Bean
    public Properties sqlSessionFactoryProperties() {
        Properties sqlSessionFactoryProperties = new Properties();

        sqlSessionFactoryProperties.put("isDeleteFalse", BaseEntity.FLAG_FALSE);
        sqlSessionFactoryProperties.put("isDeleteTrue", BaseEntity.FLAG_TRUE);
        sqlSessionFactoryProperties.put("isDeleteSqlFieldName", BaseEntity.DELETE_SQLFIELD_NAME);
        return sqlSessionFactoryProperties;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource,
                                               ApplicationContext applicationContext,
                                               Properties sqlSessionFactoryProperties) throws Exception {
        //USE LOG4J
        org.apache.ibatis.logging.LogFactory.useSlf4jLogging();
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(applicationContext.getResources("classpath*:mapper/**/*.xml"));
        sessionFactory.setConfigLocation(new ClassPathResource("config/mybatis-config.xml"));
        sessionFactory.setConfigurationProperties(sqlSessionFactoryProperties);
        return sessionFactory.getObject();
    }


    @Bean
    @DependsOn("sqlSessionFactory")
    public MapperScannerConfigurer mysqlMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        Properties properties = new Properties();
        properties.setProperty("mappers", "com.dokang.lib.mybatis.base.dao.MybatisBaseRepository");
        mapperScannerConfigurer.setProperties(properties);
        mapperScannerConfigurer.setBasePackage(env.getProperty("mybatis.MapperScan"));
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setAnnotationClass(DBMapper.class);
        return mapperScannerConfigurer;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }
}
