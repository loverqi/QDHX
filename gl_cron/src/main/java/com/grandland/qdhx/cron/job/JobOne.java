package com.grandland.qdhx.cron.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by gl-wpy on 2018/2/12 0012.
 */
public class JobOne implements Job {
    private static final Logger LOG = LoggerFactory.getLogger(JobOne.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LOG.info("-------one--------");
    }

}
