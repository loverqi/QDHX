package com.grandland.qdhx.spider.common.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

/**
 * 日照港登录状态的获取
 * @author loverqi
 * @date 2018年1月29日
 */
@Component
public class RizhaoPortLogin {

    /** session过期时间*/
    public static final long EXPIRY_TIME = 1000 * 60 * 30;

    /** 登录页的网址*/
    public static final String HOST = "http://218.56.178.124:8086";

    /** 需要返回的cookies*/
    private Map<String, String> cookies;

    /** 上次获取cookies的时间*/
    private long lastGetDate;

    /**
     * 获取登录成功的cookies
     * @param username 用户密码
     * @param password 用户密码
     * @return 登录成功的session所对应的cookie
     */
    public Map<String, String> getLoginSession(String username, String password) {

        if (cookies == null) { //未登陆过请求获取登录
            synchronized (RizhaoPortLogin.class) {
                if (cookies == null) {
                    //实际获取需要的cookie
                    doLogin(username, password);
                }
            }
        } else if (System.currentTimeMillis() - lastGetDate > EXPIRY_TIME) { //超过登录时间，重新请求登录
            //超过登录时间，重新获取cookie
            doLogin(username, password);
        }

        return cookies;
    }

    /**
     * 实际请求登录的方法
     * @param username 用户名
     * @param password 密码
     */
    private void doLogin(String username, String password) {
        // 获取响应    
        Response response = null;
        try {

            // 获取登录页
            Connection conGetLoginPage = Jsoup.connect(HOST + "/rzbot/");// 获取连接
            conGetLoginPage.header("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");
            response = conGetLoginPage.execute();
            // 转换为Dom树
            Document document = Jsoup.parse(response.body());
            List<Element> et = document.select("#J_FilterForm");
            // 获取，cooking和表单属性，下面map存放post时的数据
            Map<String, String> datas = new HashMap<>();

            for (Element e : et.get(0).getAllElements()) {
                if (e.attr("name").equals("userName")) {
                    e.attr("value", username);// 设置用户名
                }
                if (e.attr("name").equals("pswd")) {
                    e.attr("value", password); // 设置用户密码
                }
                if (e.attr("name").length() > 0 && !"button".equals(e.attr("name"))) {// 排除空值表单属性
                    datas.put(e.attr("name"), e.attr("value"));
                }
            }

            //请求登录状态
            String actionURL = et.get(0).attr("action");
            Connection conLogin = Jsoup.connect(HOST + actionURL);
            conLogin.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");

            // 发送登录请求
            conLogin.ignoreContentType(true).method(Method.POST) //请求方式
                    .data(datas) //请求数据
                    .cookies(response.cookies()) //设置cookies
                    .execute();

            //设置cookies
            this.cookies = response.cookies();
            lastGetDate = System.currentTimeMillis();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}