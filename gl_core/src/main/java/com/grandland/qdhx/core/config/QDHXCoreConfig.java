package com.grandland.qdhx.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * qdhx数据库引入配置
 */
@Configuration
@MapperScan({ "com.grandland.qdhx.core.mapper", "com.grandland.qdhx.core.mybaties.mapper" })
@ComponentScan("com.grandland.qdhx.core.manage")
public class QDHXCoreConfig {
}
