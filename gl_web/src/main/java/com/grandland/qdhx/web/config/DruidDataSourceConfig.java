package com.grandland.qdhx.web.config;

import javax.sql.DataSource;  
import org.springframework.boot.context.properties.ConfigurationProperties;  
import org.springframework.context.annotation.Bean;  
import org.springframework.context.annotation.Configuration;  
  
import com.alibaba.druid.pool.DruidDataSource;  

/**
 * druid 连接池的配置类
 * @author loverqi
 * @date 2018年1月4日
 */
@Configuration
public class DruidDataSourceConfig  {  
  
    @Bean  
    @ConfigurationProperties(prefix = "spring.datasource")  
    public DataSource druidDataSource() {  
        DruidDataSource druidDataSource = new DruidDataSource();  
        return druidDataSource;  
    }  
} 