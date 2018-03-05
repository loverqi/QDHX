package com.grandland.qdhx.spider.common.pipeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grandland.qdhx.core.mybaties.mapper.BaseMapper;
import com.grandland.qdhx.core.mybaties.pojo.MyBatisPojo;
import com.grandland.qdhx.spider.common.pipeline.base.BasePipeline;

/**
 * 将结果保存到mybaties数据库的具体实现
 * @author loverqi
 * @date 2018年1月29日
 */
@Component
public class MybatiesPipeline extends BasePipeline {

    /** 业务单相关处理mapper*/
    @Autowired
    private BaseMapper<MyBatisPojo> basemapper;

    @Override
    protected int processingData(Class<? extends MyBatisPojo> beanClass, MyBatisPojo beanValue) {
        int updateCount = basemapper.updateByPrimaryKeySelective(beanValue);
        
        if (updateCount < 1) {
            updateCount = basemapper.insertSelective(beanValue);
        }

        return updateCount;
    }
}
