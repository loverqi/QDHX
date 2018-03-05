package com.grandland.qdhx.cron.util;

import com.grandland.qdhx.core.domain.GlCronTask;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * quartz builder
 */
public class QuartzBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(QuartzBuilder.class);


    public static JobKey getJobKey(GlCronTask task) {
        return JobKey.jobKey(task.getTaskName(), task.getGroupName());
    }

    public static TriggerKey getTriggerKey(GlCronTask task) {
        return TriggerKey.triggerKey(task.getTaskName(), task.getGroupName());
    }

    @SuppressWarnings("unchecked")
    public static JobDetail getJobDetail(GlCronTask task) {
        Class<? extends Job> clazz = null;
        try {
            clazz = (Class<? extends Job>) Class.forName(task.getClazz());
        } catch (ClassNotFoundException e) {
            LOG.error("ClassNotFoundException occurs, task:{}, error:{}", task, e);
            return null;
        }
        JobDetail jobDetail = JobBuilder.newJob(clazz)
                .withIdentity(getJobKey(task))
                .withDescription(task.getTaskDesc())
                .build();
        return jobDetail;
    }

    public static CronTrigger getTrigger(GlCronTask task) {
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(getTriggerKey(task))
                .withSchedule(CronScheduleBuilder.cronSchedule(task.getCronExp()))
                .build();
        return trigger;
    }

}
