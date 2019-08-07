package com.koolearn.club.config;

import com.koolearn.framework.mybatis.datasource.KooDataSource;
import com.koolearn.framework.mybatis.spring.KooSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * Created by lvyangjun on 2018/5/10.
 */
@Configuration
@MapperScan(basePackages = {MyBatisConfig.MAPPER_PACKAGE}, sqlSessionFactoryRef = MyBatisConfig.SESSIONFACTORY_NAME)
public class MyBatisConfig {

    /**SqlSessionFactory名称.*/
    public final static String SESSIONFACTORY_NAME = "sqlSessionFactory";
    /**mapper包路径，必须与其他SqlSessionFactory-mapper路径区分.*/
    public final static String MAPPER_PACKAGE = "com.koolearn.club.mapper";
    /**mapper.xml文件路径，必须与其他SqlSessionFactory-mapper路径区分.*/
    public final static String MAPPER_XML_PATH = "classpath:mapper/*.xml";

    public final static String CONFIG_LOCATION_PATH = "classpath:mybatis-configuration.xml";



    @Bean(name = "dataSource")
    public KooDataSource dataSource() {
        //建议封装成单独的类
        KooDataSource dataSource = new KooDataSource();
        dataSource.setBizName("club");
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        KooSqlSessionFactoryBean sqlSessionFactoryBean = new KooSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(CONFIG_LOCATION_PATH));
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_XML_PATH));
        return sqlSessionFactoryBean.getObject();
    }

}
