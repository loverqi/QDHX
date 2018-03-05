package com.grandland.qdhx.web.druid.filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.alibaba.druid.support.http.WebStatFilter;

/**
 * druid连接池种的拦截器配置
 * @author loverqi
 * @date 2018年1月4日
 */
@WebFilter(filterName = "druidStatFilter", urlPatterns = "/*", //拦截器的配置
        initParams = { @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// 忽略资源
        })
public class DruidStatFilter extends WebStatFilter {

}
