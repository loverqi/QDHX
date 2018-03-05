package com.grandland.qdhx.spider.common.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grandland.qdhx.spider.common.pipeline.MybatiesPipeline;
import com.grandland.qdhx.spider.common.processor.RizhaoPortPageProcessor;

import us.codecraft.webmagic.Spider;

/**
 * 需要执行的任务
 * @author loverqi
 * @date 2018年2月12日
 */
@Component
public class RiZhaoPortTask {

    @Autowired
    private RizhaoPortPageProcessor rizhaoportpageprocessor;
    /** 页面保存逻辑*/
    @Autowired
    private MybatiesPipeline mybatiesPipeline;

    /**
     * 日照港数据启动抓取配置
     */
    public void startGrab() {
        try {
            Spider.create(rizhaoportpageprocessor)
                    //抓取的网址
                    .addUrl(RizhaoPortPageProcessor.LIST_JSON_URL)
                    //数据存取逻辑
                    .addPipeline(mybatiesPipeline)
                    //开启5个线程抓取
                    .thread(5)
                    //启动爬虫
                    .run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
