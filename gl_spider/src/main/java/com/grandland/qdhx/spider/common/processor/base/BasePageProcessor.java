package com.grandland.qdhx.spider.common.processor.base;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.grandland.qdhx.core.mybaties.pojo.MyBatisPojo;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 通用页面解析
 * @author loverqi
 * @date 2018年2月2日
 */
@Component
public abstract class BasePageProcessor implements PageProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(BasePageProcessor.class);

    /** 抓取网站的相关配置*/
    private Site site;

    /*
     * 设置登录认证信息的cookie
     * @see us.codecraft.webmagic.processor.PageProcessor#getSite()
     */
    @Override
    public Site getSite() {
        //抓取网站的相关配置，包括编码、抓取间隔、重试次数等
        if (site == null) {
            synchronized (BasePageProcessor.class) {
                if (site == null) {
                    site = Site.me().setRetryTimes(2000).setSleepTime(2000).setTimeOut(10000);
                }
            }
        }

        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    /**
     * 设置登录cookies的方法
     * @param cookies
     */
    public void setLoginCookies(Map<String, String> cookies) {
        //设置cookies
        Set<Entry<String, String>> entrySet = cookies.entrySet();
        for (Entry<String, String> entry : entrySet) {
            site.addCookie(entry.getKey(), entry.getValue());
        }
    }

    /**
     * 将需要的数据交给保存器
     */
    protected void putBeanToPage(Page page, MyBatisPojo obj) {
        Integer beanSize = page.getResultItems().get("beanSize");
        beanSize = beanSize == null ? 0 : beanSize;

        if (page != null && obj != null) {
            page.putField("beanSize", beanSize + 1);

            page.putField("beanClass_" + beanSize, obj.getClass());
            page.putField("beanValue_" + beanSize, obj);

            LOG.info("input one Object: " + obj.getClass().getName());
        } else {
            LOG.info("the Object is empty");
        }
    }

    /**
     * 将需要的数据交给保存器
     */
    protected void putBeanToPage(Page page, List<? extends MyBatisPojo> objs) {
        if (objs != null) {
            for (MyBatisPojo myBatisPojo : objs) {
                putBeanToPage(page, myBatisPojo);
            }
        }
    }
}