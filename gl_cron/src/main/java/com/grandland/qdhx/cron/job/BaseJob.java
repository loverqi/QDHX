package com.grandland.qdhx.cron.job;

import com.grandland.qdhx.client.app.PushClient;
import com.grandland.qdhx.core.domain.BusinessMortgage;
import com.grandland.qdhx.core.domain.GlCronTask;
import com.grandland.qdhx.core.mapper.BusinessMortgageMapper;
import com.grandland.qdhx.core.mapper.CronTaskMapper;
import com.grandland.qdhx.core.mybaties.pojo.Example;
import com.grandland.qdhx.core.utils.ConstantUtil;
import com.grandland.qdhx.core.utils.PrimaryKey;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.util.List;

/**
 * Job父类
 */
public abstract class BaseJob implements Job {
    private static final Logger LOG = LoggerFactory.getLogger(BaseJob.class);
    private static final int STATE = ConstantUtil.NUMBER_FOUR;

    protected String jobType = null;

    @Autowired
    private BusinessMortgageMapper mortgageMapper;

    @Autowired
    private CronTaskMapper cronTaskMapper;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Example criteria = new Example();
        criteria.createCriteria().andFieldEqualTo(PrimaryKey.BUSINESS_STATE, STATE);
        BusinessMortgage business = new BusinessMortgage();
        List<BusinessMortgage> list = mortgageMapper.selectByExample(business, criteria);
        if (ObjectUtils.isEmpty(list)) {
            LOG.warn("No approved business, do not need work!!");
            return;
        }
        for (BusinessMortgage mortgage : list) {
            if (!isMatchCond(mortgage)) {
                continue;
            }
            handler(mortgage);
        }
        LOG.info("cron business finished!");
    }


    /**
     * 判断是否符合业务条件(比如跌价补偿业务,判断是否跌价)
     * @param mortgage
     * @return
     */
    protected abstract boolean isMatchCond(BusinessMortgage mortgage);

    /**
     * 具体操作(比如跌价补偿业务,跌价之后的操作)
     * @param mortgage
     */
    protected abstract void handler(BusinessMortgage mortgage);


    /**
     * 获取job类型
     * @return
     */
    protected String getJobType() {
        if (jobType == null) {
            GlCronTask cronTask = new GlCronTask();
            Example criteria = new Example();
            criteria.createCriteria().andFieldEqualTo("clazz", getClass().getName());
            List<GlCronTask> list = cronTaskMapper.selectByExample(cronTask, criteria);
            if (ObjectUtils.isEmpty(list)) {
                LOG.error("job[{}] type is null, please check!!", getClass().getName());
                return null;
            }
            cronTask = list.get(0);
            if (!ObjectUtils.isEmpty(cronTask)) {
                jobType = cronTask.getTaskType();
            }
        }
        return jobType;
    }


    /**
     * 推送提示消息至APP
     * @param messageId
     * @param businessId
     * @param userId
     * @param remindMsg
     * @param mortgage
     * @return
     */
    protected boolean pushMsgToApp(Integer messageId,
                                   Integer businessId,
                                   String userId,
                                   String remindMsg,
                                   BusinessMortgage mortgage) {
        return PushClient.pushRemidMsg(String.valueOf(messageId),
                                       String.valueOf(businessId),
                                       getJobType(),
                                       userId,
                                       remindMsg,
                                       getDetailHtml(mortgage));
    }

    /**
     * 获取业务详情HTML
     * @param mortgage
     * @return
     */
    protected String getDetailHtml(BusinessMortgage mortgage) {
        //TODO 完善业务详情表格
        return "<table></table>";
    }


    /**
     * 自动注入使用
     */
    protected void springBeanAutowiringSupport() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }


}
