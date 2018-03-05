package com.grandland.qdhx.cron;

import com.grandland.qdhx.core.config.QDHXCoreConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * 定时业务程序
 * @date 2018.02.15
 */
@Import(QDHXCoreConfig.class)
@SpringBootApplication
public class CronApplication  implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(CronApplication.class);

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(CronApplication.class);
        application.setWebEnvironment(false);
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("cron start....");
    }
}
