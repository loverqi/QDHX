package com.grandland.qdhx.spider;

import com.grandland.qdhx.core.config.QDHXCoreConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 项目入口
 * @author loverqi
 * @date 2018年1月24日
 */
@Import(QDHXCoreConfig.class)
@EnableScheduling //定时任务执行
@SpringBootApplication
public class SpiderApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpiderApplication.class);
        application.setWebEnvironment(false);
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}