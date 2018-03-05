package com.grandland.qdhx.web;

import com.grandland.qdhx.core.config.QDHXCoreConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 开发环境入口类
 * @author loverqi
 * @date 2018年1月4日
 */
@Import(QDHXCoreConfig.class)
@ServletComponentScan
@SpringBootApplication
@EnableSwagger2 //启动swagger注解
public class WebApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class);
    }

}
