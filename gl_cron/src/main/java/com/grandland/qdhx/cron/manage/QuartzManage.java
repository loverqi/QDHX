package com.grandland.qdhx.cron.manage;

import com.grandland.qdhx.core.domain.GlCronTask;
import com.grandland.qdhx.core.mapper.CronTaskMapper;
import com.grandland.qdhx.cron.util.QuartzBuilder;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 定时任务创建并刷新
 */
@Component
public class QuartzManage {
    private static final Logger LOG = LoggerFactory.getLogger(QuartzManage.class);

    @Resource(name = "scheduler")
    private Scheduler scheduler;

    @Autowired
    private CronTaskMapper cronTaskMapper;

    @Scheduled(fixedRateString = "${scheduler.refresh.period}")
    public void refresh() {
        load();
    }

    public void load() {
        LOG.info("refresh the scheduler...");
        List<GlCronTask> list = cronTaskMapper.findAll();
        if (!ObjectUtils.isEmpty(list)) {
            LOG.info("taskList.size:{}", list.size());
            for (GlCronTask task : list) {
                if (task == null) {
                    continue;
                }
                try {
                    //通过TriggerKey获取调度器中已注册的trigger
                    JobDetail jobDetail = scheduler.getJobDetail(QuartzBuilder.getJobKey(task));
                    Trigger trigger = scheduler.getTrigger(QuartzBuilder.getTriggerKey(task));
                    if (ObjectUtils.isEmpty(trigger)) {
                        //如果trigger为空,则表明job未注册
                        if (task.isEnable()) {
                            register(task);
                        }
                    } else {
                        //如果不为空,则表明job已注册,新属性值与原属性值比对,以更新任务
                        SchedulerUpdate(task, (CronTrigger) trigger, jobDetail);
                    }
                    //判断是否需要立即执行
                    if (task.isRunNow()) {
                        runNow(task);
                    }
                } catch (SchedulerException e) {
                    LOG.error("SchedulerException occrus, task:{}, error:{}", task, e);
                }
            }
        } else {
            LOG.warn("enable cron task is null,please check!");
        }
        LOG.info("refresh the scheduler finished");
    }


    /**
     * 根据属性值判断任务是否更新
     * @param task
     * @param trigger
     */
    public void SchedulerUpdate(GlCronTask task, CronTrigger trigger, JobDetail jobDetail) {
        Class<? extends Job> clazz = jobDetail.getJobClass();
        String cronExp = trigger.getCronExpression();
        if (!task.isEnable()) {
            //根据enable判断是否更新
            //如果不可用，则删除任务
            deleteJob(task);
        } else if (!clazz.getName().equals(task.getClazz())) {
            //根据clazz判断是否更新
            //如果不同，则更新任务
            updateJob(task);
        } else if (!cronExp.equals(task.getCronExp())) {
            //根据cron表达式判断是否更新
            //如果不同，则更新trigger
            updateTrigger(task);
        }
    }


    /**
     * 新增Job
     * @param task
     */
    public void register(GlCronTask task) {
        JobDetail jobDetail = QuartzBuilder.getJobDetail(task);
        CronTrigger trigger = QuartzBuilder.getTrigger(task);
        if (jobDetail != null && trigger != null) {
            try {
                scheduler.scheduleJob(jobDetail, trigger);
                LOG.info("add job success! task:{}", task);
            } catch (SchedulerException e) {
                LOG.error("SchedulerException occrus, task:{}, error:{}", task, e);
            }
        }
    }

    /**
     * 删除Job
     * @param task
     */
    public void deleteJob(GlCronTask task) {
        try {
            scheduler.deleteJob(QuartzBuilder.getJobKey(task));
            LOG.info("delete job success! task:{}", task);
        } catch (SchedulerException e) {
            LOG.error("SchedulerException occrus, task:{}, error:{}", task, e);
        }
    }

    /**
     * 更新Job，即删除Job后重新注册
     * @param task
     */
    public void updateJob(GlCronTask task) {
        deleteJob(task);
        register(task);
    }

    /**
     * 更新trigger
     * @param task
     */
    public void updateTrigger(GlCronTask task) {
        CronTrigger trigger = QuartzBuilder.getTrigger(task);
        if (trigger != null) {
            try {
                scheduler.rescheduleJob(QuartzBuilder.getTriggerKey(task), trigger);
                LOG.info("re scheduler job success! task:{}", task);
            } catch (SchedulerException e) {
                LOG.error("SchedulerException occrus, task:{}, error:{}", task, e);
            }
        }
    }

    /**
     * 立即运行任务
     * @param task
     */
    public void runNow(GlCronTask task) {
        try {
            LOG.info("run now success! task:{}", task);
            scheduler.triggerJob(QuartzBuilder.getJobKey(task));
            cronTaskMapper.updateRunNow(task.getId(), false);
        } catch (SchedulerException e) {
            LOG.error("SchedulerException occrus, task:{}, error:{}", task, e);
        }
    }




}
