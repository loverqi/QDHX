package com.grandland.qdhx.spider.common.pipeline.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.grandland.qdhx.core.mybaties.pojo.MyBatisPojo;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * 将结果保存到mybaties数据库的具体实现
 * @author loverqi
 * @date 2018年1月29日
 */
@Component
public abstract class BasePipeline implements Pipeline {
    private static final Logger LOG = LoggerFactory.getLogger(BasePipeline.class);

    @Override
    public void process(ResultItems resultItems, Task task) {

        //获取需要储存的数据的个数
        Integer beanSize = resultItems.get("beanSize");
        if (beanSize == null) {
            return;
        }

        Class<? extends MyBatisPojo> beanClass = null;
        MyBatisPojo beanValue = null;
        //循环箱数据库存储数据
        for (int i = 0; i < beanSize; i++) {

            beanClass = resultItems.get("beanClass_" + i);
            beanValue = resultItems.get("beanValue_" + i);

            if (beanClass == null || beanValue == null) {
                continue;
            }

            //调用模板方法执行数据处理类
            int successCount = processingData(beanClass, beanValue);
            LOG.info(beanClass.getName()+"update: "+ (successCount>0?"success":"error"));
        }

    }

    /**
     * 数据的模板处理类
     * @param beanClass 要处理的对象的类型
     * @param beanValue 要处理的对象的对象值
     * @return
     */
    protected abstract int processingData(Class<? extends MyBatisPojo> beanClass, MyBatisPojo beanValue);

}
