package com.grandland.qdhx.cron.config;

import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * quartz配置
 */
@Configuration
public class QuartzConfig {
    private static final Logger LOG = LoggerFactory.getLogger(QuartzConfig.class);

    @Bean(name = "scheduler")
    public Scheduler scheduler(QuartzJobFactory quartzJobFactory) {
        Scheduler scheduler = null;
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setJobFactory(quartzJobFactory);
        try {
            factoryBean.afterPropertiesSet();
            scheduler = factoryBean.getScheduler();
            scheduler.start();
        } catch (Exception e) {
            LOG.error("exception occurs:{}", e);
        }
        return scheduler;
    }

}
